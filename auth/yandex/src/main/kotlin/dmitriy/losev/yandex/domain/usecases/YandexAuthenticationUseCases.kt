package dmitriy.losev.yandex.domain.usecases

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthSdk
import com.yandex.authsdk.internal.strategy.LoginType
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseTokenAuthUseCase
import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.core.exception.YandexNullTokenException
import dmitriy.losev.yandex.domain.models.YandexToken

class YandexAuthenticationUseCases(
    private val yandexAuthSDK: YandexAuthSdk,
    private val yandexTokenUseCase: YandexTokenUseCase,
    private val yandexDataUseCase: YandexDataUseCase,
    private val yandexUpdateInformationUseCase: YandexUpdateInformationUseCase,
    private val firebaseTokenAuthUseCase: FirebaseTokenAuthUseCase
) : YandexBaseUseCase() {

    suspend fun authWithYandexIntent(
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        authenticationListener: AuthenticationListener
    ) {
        val loginOptions = YandexAuthLoginOptions.Builder().setLoginType(LoginType.NATIVE).build()
        val intent = yandexAuthSDK.createLoginIntent(loginOptions)
        launcher.launch(intent)
        authenticationListener.onLoading()
    }

    suspend fun authWithYandexIntent(
        resultCode: Int,
        intent: Intent?,
    ) {
        val yandexToken = authWithYandexToken(resultCode, intent)
        val firebaseToken = yandexTokenUseCase.createToken(yandexToken)
        firebaseTokenAuthUseCase.authWithToken(firebaseToken)
        val yandexUserData = yandexDataUseCase.getYandexUserData(yandexToken)
        yandexUpdateInformationUseCase.updateInformation(
            email = yandexUserData.email,
            firstName = yandexUserData.firstName,
            lastName = yandexUserData.lastName,
            avatarUrl = getYandexAvatarUrl(yandexUserData.avatarId)
        )
    }

    private fun authWithYandexToken(resultCode: Int, intent: Intent?): YandexToken {
        return yandexAuthSDK.extractToken(resultCode, intent)?.let { token -> YandexToken(yandexAuthSDK.getJwt(token)) } ?: throw YandexNullTokenException()
    }

    private fun getYandexAvatarUrl(avatarId: String): String {
        return YANDEX_AVATAR_URL.replace(oldValue = ID, newValue = avatarId)
    }

    companion object {
        private const val ID = "id"
        private const val YANDEX_AVATAR_URL = "https://avatars.yandex.net/get-yapic/id/islands-200"
    }
}
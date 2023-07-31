package dmitriy.losev.yandex.domain.usecases

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthSdk
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.core.exception.YandexNullTokenException

class YandexAuthenticationUseCases(
    errorHandler: ErrorHandler,
    private val yandexAuthSDK: YandexAuthSdk
): YandexBaseUseCase(errorHandler = errorHandler) {

    suspend fun authWithYandex(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) = safeCall {
        val loginOptionsBuilder = YandexAuthLoginOptions.Builder()
        val intent = yandexAuthSDK.createLoginIntent(loginOptionsBuilder.build())
        launcher.launch(intent)
    }

    suspend fun authWithYandexIntent(resultCode: Int, intent: Intent?) = safeCall {
        val yandexAuthToken = yandexAuthSDK.extractToken(resultCode, intent)
        yandexAuthToken?.let {
            yandexAuthSDK.getJwt(it)
        } ?: throw YandexNullTokenException()
    }
}
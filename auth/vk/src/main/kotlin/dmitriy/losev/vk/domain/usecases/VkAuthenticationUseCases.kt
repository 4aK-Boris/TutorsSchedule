package dmitriy.losev.vk.domain.usecases

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import dmitriy.losev.core.core.AuthenticationListener
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseTokenAuthUseCase
import dmitriy.losev.vk.core.VkBaseUseCase
import dmitriy.losev.vk.presentation.ui.VkActivity

class VkAuthenticationUseCases(
    errorHandler: ErrorHandler,
    private val context: Context,
    private val vkTokenUseCase: VkTokenUseCase,
    private val vkUserDataUseCase: VkUserDataUseCase,
    private val vkUpdateInformationUseCase: VkUpdateInformationUseCase,
    private val firebaseTokenAuthUseCase: FirebaseTokenAuthUseCase,
) : VkBaseUseCase(errorHandler) {

    suspend fun authWithVk(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>, authenticationListener: AuthenticationListener) = safeCall {
        launcher.launch(Intent(context, VkActivity::class.java))
        authenticationListener.onLoading()
    }

    suspend fun authWithVk(userId: Long, token: String, email: String?): Result<Unit> = safeReturnCall {
        vkUserDataUseCase.getVkUserData(userId, token, email).processingResult { vkUserData ->
            vkTokenUseCase.getToken(vkUserData).processingResult { firebaseToken ->
                firebaseTokenAuthUseCase.authWithToken(firebaseToken).processingResult { user ->
                    vkUpdateInformationUseCase.updateInformation(
                        user = user,
                        email = vkUserData.email,
                        firstName = vkUserData.firstName,
                        lastName = vkUserData.lastName,
                        avatarUrl = vkUserData.photoUrl
                    )
                }
            }
        }
    }
}
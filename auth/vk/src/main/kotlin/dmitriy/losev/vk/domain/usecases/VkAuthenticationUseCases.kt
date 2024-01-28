package dmitriy.losev.vk.domain.usecases

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseTokenAuthUseCase
import dmitriy.losev.vk.core.VkBaseUseCase
import dmitriy.losev.vk.presentation.ui.VkActivity

class VkAuthenticationUseCases(
    private val context: Context,
    private val vkTokenUseCase: VkTokenUseCase,
    private val vkUserDataUseCase: VkUserDataUseCase,
    private val vkUpdateInformationUseCase: VkUpdateInformationUseCase,
    private val firebaseTokenAuthUseCase: FirebaseTokenAuthUseCase
) : VkBaseUseCase() {

    suspend fun authWithVk(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>, authenticationListener: AuthenticationListener) {
        launcher.launch(Intent(context, VkActivity::class.java))
        authenticationListener.onLoading()
    }

    suspend fun authWithVk(userId: Long, token: String, email: String?) {
        val vkUserData = vkUserDataUseCase.getVkUserData(userId, token, email)
        val firebaseToken = vkTokenUseCase.getToken(vkUserData)
        firebaseTokenAuthUseCase.authWithToken(firebaseToken)
        vkUpdateInformationUseCase.updateInformation(
            email = vkUserData.email,
            firstName = vkUserData.firstName,
            lastName = vkUserData.lastName,
            avatarUrl = vkUserData.photoUrl
        )
    }
}
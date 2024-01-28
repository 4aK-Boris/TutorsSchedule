package dmitriy.losev.vk.presentation.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackground
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.vk.domain.usecases.VkAuthenticationUseCases
import dmitriy.losev.vk.presentation.ui.VkActivity

class VkAuthenticationViewModel(private val vkAuthenticationUseCases: VkAuthenticationUseCases) : BaseViewModel() {

    fun authWithVk(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>, authenticationListener: AuthenticationListener) = runOnBackground {
        safeCall { vkAuthenticationUseCases.authWithVk(launcher, authenticationListener) }.processing()
    }

    fun authWithVkIntent(result: ActivityResult, authenticationListener: AuthenticationListener) = runOnMain {
        if (result.resultCode == Activity.RESULT_OK) {
            val token = result.data?.getStringExtra(VkActivity.TOKEN)
            val uId = result.data?.getLongExtra(VkActivity.UID, DEFAULT_UID)
            val email = result.data?.getStringExtra(VkActivity.EMAIL)
            if (token == null || uId == null) {
                authenticationListener.onError()
            } else {
                safeCall {
                    vkAuthenticationUseCases.authWithVk(userId = uId, token = token, email = email)
                }.processing(onError = authenticationListener::onError) {
                    authenticationListener.onSuccess()
                }
            }
        } else {
            authenticationListener.onError()
        }
    }

    companion object {
        private const val DEFAULT_UID = 0L
    }
}
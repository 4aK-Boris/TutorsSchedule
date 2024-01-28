package dmitriy.losev.yandex.presentation.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackground
import dmitriy.losev.yandex.domain.usecases.YandexAuthenticationUseCases

class YandexAuthenticationViewModel(private val yandexAuthenticationUseCases: YandexAuthenticationUseCases) : BaseViewModel() {

    fun authWithYandex(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>, authenticationListener: AuthenticationListener) = runOnBackground {
        safeCall { yandexAuthenticationUseCases.authWithYandexIntent(launcher, authenticationListener) }.processing()
    }

    fun authWithYandexIntent(result: ActivityResult, authenticationListener: AuthenticationListener) = runOnBackground {
        if (result.resultCode == Activity.RESULT_OK) {
            safeCall {
                yandexAuthenticationUseCases.authWithYandexIntent(resultCode = result.resultCode, intent = result.data)
            }.processing(onError = authenticationListener::onError) {
                authenticationListener.onSuccess()
            }
        } else {
            authenticationListener.onError()
        }
    }
}
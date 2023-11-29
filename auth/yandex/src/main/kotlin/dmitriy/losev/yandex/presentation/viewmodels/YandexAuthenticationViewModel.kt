package dmitriy.losev.yandex.presentation.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import dmitriy.losev.core.core.AuthenticationListener
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.yandex.domain.usecases.YandexAuthenticationUseCases

class YandexAuthenticationViewModel(
    errorHandler: ErrorHandler,
    private val yandexAuthenticationUseCases: YandexAuthenticationUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    fun authWithYandex(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>, authenticationListener: AuthenticationListener) = processing {
        yandexAuthenticationUseCases.authWithYandexIntent(launcher, authenticationListener)
    }

    fun authWithYandexIntent(result: ActivityResult, authenticationListener: AuthenticationListener) = runOnBackground {
        if (result.resultCode == Activity.RESULT_OK) {
            yandexAuthenticationUseCases.authWithYandexIntent(
                resultCode = result.resultCode,
                intent = result.data
            ).processing(onError = authenticationListener::onError) {
                authenticationListener.onSuccess()
            }
        } else {
            authenticationListener.onError()
        }
    }
}
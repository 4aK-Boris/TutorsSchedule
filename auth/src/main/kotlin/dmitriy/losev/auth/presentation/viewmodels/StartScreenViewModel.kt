package dmitriy.losev.auth.presentation.viewmodels

import android.app.Activity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.domain.usecases.AuthenticationGoogleUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationListenerUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.core.core.AuthenticationListener
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.firebase.core.exception.API_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.GOOGLE_AUTH_IS_NOT_SUCCESS_EXCEPTION_CODE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartScreenViewModel(
    errorHandler: ErrorHandler,
    private val authenticationListenerUseCase: AuthenticationListenerUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationGoogleUseCase: AuthenticationGoogleUseCase
) : BaseViewModel(errorHandler = errorHandler) {

    private val _isLoading = MutableStateFlow(value = false)

    val isLoading = _isLoading.asStateFlow()

    private fun onIsLoadingChanged(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun authWithGoogle(client: SignInClient, launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) = processing {
        authenticationGoogleUseCase.authWithGoogle(client = client, launcher = launcher)
    }

    fun authWithGoogleIntent(
        result: ActivityResult,
        client: SignInClient,
        authenticationNavigationListener: AuthenticationNavigationListener
    ) = runOnBackground {
        if (result.resultCode == Activity.RESULT_OK) {
            authenticationGoogleUseCase.authWithGoogleIntent(intent = result.data, client, authenticationNavigationListener).processing()
        }
    }

    fun getAuthenticationListener(authenticationNavigationListener: AuthenticationNavigationListener): AuthenticationListener {
        return authenticationListenerUseCase.getAuthenticationListener(authenticationNavigationListener, ::onIsLoadingChanged)
    }

    fun navigateToLoginScreen(authenticationNavigationListener: AuthenticationNavigationListener) = processing {
        authenticationNavigationUseCases.navigateToLoginScreen(authenticationNavigationListener)
    }

    fun navigateToDataScreen(authenticationNavigationListener: AuthenticationNavigationListener) = processing {
        authenticationNavigationUseCases.navigateToDataScreen(authenticationNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            API_EXCEPTION_CODE to R.string.api_exception_message,
            GOOGLE_AUTH_IS_NOT_SUCCESS_EXCEPTION_CODE to R.string.google_auth_is_not_success_exception_message
        )
}

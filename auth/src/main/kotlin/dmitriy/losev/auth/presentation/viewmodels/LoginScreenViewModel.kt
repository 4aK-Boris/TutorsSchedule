package dmitriy.losev.auth.presentation.viewmodels

import android.app.Activity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.auth.core.exceptions.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.USER_AVAILABLE_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationEmailAuthUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationGoogleUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationListenerUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.core.AuthenticationListener
import dmitriy.losev.firebase.core.exception.API_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_NETWORK_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE
import dmitriy.losev.network.exception.CONNECTION_EXCEPTION_CODE
import dmitriy.losev.network.exception.TIMEOUT_EXCEPTION_CODE
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackground
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginScreenViewModel(
    private val authenticationEmailAuthUseCase: AuthenticationEmailAuthUseCase,
    private val authenticationListenerUseCase: AuthenticationListenerUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationGoogleUseCase: AuthenticationGoogleUseCase
) : BaseViewModel() {

    private val _email = MutableStateFlow(value = EMPTY_STRING)
    private val _password = MutableStateFlow(value = EMPTY_STRING)

    private val _emailTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _passwordTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    private val _passwordVisible = MutableStateFlow(value = false)

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    val emailTextFieldState = _emailTextFieldState.asStateFlow()
    val passwordTextFieldState = _passwordTextFieldState.asStateFlow()

    val passwordVisible = _passwordVisible.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun onPasswordVisibleChanged(passwordVisible: Boolean) {
        _passwordVisible.value = passwordVisible
    }

    fun clearEmailTextFieldState() {
        _emailTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearPasswordTextFieldState() {
        _passwordTextFieldState.value = TextFieldState.DEFAULT
    }

    fun authWithEmail(authenticationNavigationListener: AuthenticationNavigationListener) = runOnIOWithLoading {
        val email = _email.value
        val password = _password.value
        safeCall { authenticationEmailAuthUseCase.authWithEmail(email, password) }.processingLoading {
            authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener)
        }
    }

    fun navigateToPasswordResetScreen(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        val email = _email.value.ifBlank { null }
        authenticationNavigationUseCases.navigateToPasswordResetScreen(authenticationNavigationListener, email)
    }

    fun authWithGoogleIntent(authenticationNavigationListener: AuthenticationNavigationListener, activityResult: ActivityResult, client: SignInClient) =
        runOnBackground {
            if (activityResult.resultCode == Activity.RESULT_OK) {
                safeCall {
                    authenticationGoogleUseCase.authWithGoogleIntent(activityResult.data, client)
                }.processing {
                    authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener)
                }
            }
        }

    fun authWithGoogle(client: SignInClient, launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) = runOnBackground {
        safeCall { authenticationGoogleUseCase.authWithGoogle(client, launcher) }.processing()
    }

    fun getAuthenticationListener(authenticationNavigationListener: AuthenticationNavigationListener): AuthenticationListener {
        return authenticationListenerUseCase.getAuthenticationListener(authenticationNavigationListener, ::onIsLoadingChanged)
    }

    fun back(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        authenticationNavigationUseCases.back(authenticationNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMPTY_PASSWORD_EXCEPTION_CODE to R.string.empty_password_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE to R.string.firebase_auth_invalid_user_exception_message,
            FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE to R.string.firebase_auth_invalid_credentials_exception_message,
            FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE to R.string.firebase_too_many_requests_exception_message,
            API_EXCEPTION_CODE to R.string.api_exception_message,
            USER_AVAILABLE_EXCEPTION_CODE to R.string.user_available_exception_message,
            CONNECTION_EXCEPTION_CODE to R.string.connection_exception_message,
            TIMEOUT_EXCEPTION_CODE to R.string.timeout_exception_code,
            FIREBASE_NETWORK_EXCEPTION_CODE to R.string.firebase_network_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.ERROR },
            EMPTY_PASSWORD_EXCEPTION_CODE to { _passwordTextFieldState.value = TextFieldState.ERROR },
            EMAIL_VALIDATION_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.ERROR },
            FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE to {
                _emailTextFieldState.value = TextFieldState.WARNING
                _passwordTextFieldState.value = TextFieldState.WARNING
            }
        )
}
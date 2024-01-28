package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.auth.core.exceptions.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.USER_ABSENCE_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationPasswordResetUseCase
import dmitriy.losev.firebase.core.exception.FIREBASE_NETWORK_EXCEPTION_CODE
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordResetScreenViewModel(
    private val authenticationPasswordResetUseCase: AuthenticationPasswordResetUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : BaseViewModel() {

    private val _email = MutableStateFlow(value = EMPTY_STRING)

    private val _emailTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    val email = _email.asStateFlow()

    val emailTextFieldState = _emailTextFieldState.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun clearEmailTextFieldState() {
        _emailTextFieldState.value = TextFieldState.DEFAULT
    }

    fun resetPassword(authenticationNavigationListener: AuthenticationNavigationListener) = runOnIOWithLoading {
        val email = _email.value
        safeCall { authenticationPasswordResetUseCase.resetPassword(email) }.processingLoading {
            authenticationNavigationUseCases.navigateToLoginScreen(authenticationNavigationListener)
        }
    }

    fun back(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        authenticationNavigationUseCases.back(authenticationNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            USER_ABSENCE_EXCEPTION_CODE to R.string.user_absence_exception_message,
            FIREBASE_NETWORK_EXCEPTION_CODE to R.string.firebase_network_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.ERROR },
            EMAIL_VALIDATION_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.ERROR },
            USER_ABSENCE_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.WARNING }
        )
}
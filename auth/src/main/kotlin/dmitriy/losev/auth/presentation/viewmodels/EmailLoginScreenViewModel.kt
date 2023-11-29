package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.exceptions.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailAuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmailLoginScreenViewModel(
    errorHandler: ErrorHandler,
    private val firebaseEmailAuthUseCase: FirebaseEmailAuthUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _email = MutableStateFlow(value = "")
    private val _password = MutableStateFlow(value = "")

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun authWithEmail(authenticationNavigationListener: AuthenticationNavigationListener) = runOnBackground {
        val email = _email.value
        val password = _password.value
        firebaseEmailAuthUseCase.authWithEmail(email, password).processing {
            authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener).processing()
        }
    }

    fun navigateToPasswordResetScreen(authenticationNavigationListener: AuthenticationNavigationListener) = processing {
        val email = _email.value.ifBlank { null }
        authenticationNavigationUseCases.navigateToPasswordResetScreen(authenticationNavigationListener, email)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE to R.string.firebase_auth_invalid_user_exception_message,
            FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE to R.string.firebase_auth_invalid_credentials_exception_message,
            FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE to R.string.firebase_too_many_requests_exception_message
        )
}
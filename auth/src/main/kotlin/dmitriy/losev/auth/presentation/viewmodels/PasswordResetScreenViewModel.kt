package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.exceptions.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.USER_ABSENCE_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationPasswordResetUseCase
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.auth.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordResetScreenViewModel(
    errorHandler: ErrorHandler,
    private val authenticationPasswordResetUseCase: AuthenticationPasswordResetUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : BaseViewModel(errorHandler = errorHandler)  {

    private val _email = MutableStateFlow(value = "")

    val email = _email.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun resetPassword(authenticationNavigationListener: AuthenticationNavigationListener) = runOnBackground {
        val email = _email.value
        authenticationPasswordResetUseCase.resetPassword(email).processing {
            authenticationNavigationUseCases.navigateToLoginScreen(authenticationNavigationListener).processing()
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            USER_ABSENCE_EXCEPTION_CODE to R.string.user_absence_exception_message
        )
}
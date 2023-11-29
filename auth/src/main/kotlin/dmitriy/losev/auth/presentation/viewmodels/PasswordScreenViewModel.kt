package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.exceptions.DIFFERENT_PASSWORDS_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MAX_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MIN_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationRegistrationUseCase
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.firebase.domain.models.UserDescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordScreenViewModel(
    errorHandler: ErrorHandler,
    private val authenticationRegistrationUseCase: AuthenticationRegistrationUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : BaseViewModel(errorHandler) {

    private val _password1 = MutableStateFlow(value = "")
    private val _password2 = MutableStateFlow(value = "")
    private val _isLoading = MutableStateFlow(value = false)

    val password1 = _password1.asStateFlow()
    val password2 = _password2.asStateFlow()
    val isLoading = _isLoading.asStateFlow()



    fun onPassword1Changed(password: String) {
        _password1.value = password
    }

    fun onPassword2Changed(password: String) {
        _password2.value = password
    }

    fun registration(userDescription: UserDescription, authenticationNavigationListener: AuthenticationNavigationListener) = runOnBackground {
        _isLoading.value = true
        val password1 = _password1.value
        val password2 = _password2.value
        authenticationRegistrationUseCase.registration(userDescription, password1, password2).processing(onError = {
            _isLoading.value = false
        }) {
            _isLoading.value = false
            authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener).processing()
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_PASSWORD_EXCEPTION_CODE to R.string.empty_password_exception_message,
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.min_length_password_exception_message,
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.max_length_password_exception_message,
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to R.string.password_lower_case_letter_exception_message,
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to R.string.password_upper_case_letter_exception_message,
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to R.string.different_password_exception_message
        )
}
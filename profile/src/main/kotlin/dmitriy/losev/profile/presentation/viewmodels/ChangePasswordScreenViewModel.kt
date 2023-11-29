package dmitriy.losev.profile.presentation.viewmodels

import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.core.core.runOnMain
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ButtonBackgroundState
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.core.exception.DIFFERENT_PASSWORDS_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.EMPTY_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.FIREBASE_AUTH_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.MAX_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.MIN_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.profile.domain.usecases.ProfileChangePasswordUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChangePasswordScreenViewModel(
    errorHandler: ErrorHandler,
    private val profileChangePasswordUseCase: ProfileChangePasswordUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _password1 = MutableStateFlow(value = "")
    private val _password2 = MutableStateFlow(value = "")

    private val _buttonBackgroundState = MutableStateFlow(value = ButtonBackgroundState.DEFAULT)

    val password1 = _password1.asStateFlow()
    val password2 = _password2.asStateFlow()

    val buttonBackgroundState = _buttonBackgroundState.asStateFlow()

    fun opPassword1Changed(password: String) {
        _password1.value = password
    }

    fun opPassword2Changed(password: String) {
        _password2.value = password
    }

    fun changePassword(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        val password1 = _password1.value
        val password2 = _password2.value
        profileChangePasswordUseCase.changePassword(password1, password2, profileNavigationListener)
            .processing(
                onError = { _buttonBackgroundState.value = ButtonBackgroundState.ERROR },
                onSuccess = { _buttonBackgroundState.value = ButtonBackgroundState.SUCCESS }
            )
    }

    fun navigateToEditProfileScreen(profileNavigationListener: ProfileNavigationListener) =
        runOnMain {
            profileNavigationUseCases.navigateToEditProfileScreen(profileNavigationListener)
                .processing()
        }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            FIREBASE_AUTH_EXCEPTION_CODE to R.string.firebase_auth_exception_message_in_chenge_password_screen,
            EMPTY_PASSWORD_EXCEPTION_CODE to R.string.empty_password_exception_message,
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.min_length_password_exception_message,
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.max_length_password_exception_message,
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to R.string.password_lower_case_letter_exception_message,
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to R.string.password_upper_case_letter_exception_message,
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to R.string.different_password_exception_message
        )

}
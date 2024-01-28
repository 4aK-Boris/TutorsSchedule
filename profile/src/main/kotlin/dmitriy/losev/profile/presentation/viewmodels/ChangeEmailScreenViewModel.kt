package dmitriy.losev.profile.presentation.viewmodels

import dmitriy.losev.firebase.core.exception.FIREBASE_NETWORK_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.USER_NOT_AUTHORIZATION_EXCEPTION_CODE
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.EMPTY_STRING
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.core.exception.EMPTY_NEW_EMAIL_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.EMPTY_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.NEW_EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.OLD_EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.USER_ABSENCE_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.USER_AVAILABLE_EXCEPTION_CODE
import dmitriy.losev.profile.domain.usecases.ProfileChangeEmailUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackgroundWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChangeEmailScreenViewModel(
    private val profileNavigationUseCases: ProfileNavigationUseCases,
    private val profileChangeEmailUseCase: ProfileChangeEmailUseCase
) : BaseViewModel() {

    private val _oldEmail = MutableStateFlow(value = EMPTY_STRING)
    private val _newEmail = MutableStateFlow(value = EMPTY_STRING)
    private val _password = MutableStateFlow(value = EMPTY_STRING)

    private val _oldEmailTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _newEmailTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _passwordTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    private val _passwordVisible = MutableStateFlow(value = false)

    val oldEmail = _oldEmail.asStateFlow()
    val newEmail = _newEmail.asStateFlow()
    val password = _password.asStateFlow()

    val oldEmailTextFieldState = _oldEmailTextFieldState.asStateFlow()
    val newEmailTextFieldState = _newEmailTextFieldState.asStateFlow()
    val passwordTextFieldState = _passwordTextFieldState.asStateFlow()

    val passwordVisible = _passwordVisible.asStateFlow()

    fun onOldEmailChanged(email: String) {
        _oldEmail.value = email
    }

    fun onNewEmailChanged(email: String) {
        _newEmail.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun clearOldEmailTextFieldState() {
        _oldEmailTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearNewEmailTextFieldState() {
        _newEmailTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearPasswordTextFieldState() {
        _passwordTextFieldState.value = TextFieldState.DEFAULT
    }

    fun onPasswordVisibleChanged(passwordVisible: Boolean) {
        _passwordVisible.value = passwordVisible
    }

    fun changeEmail(profileNavigationListener: ProfileNavigationListener) = runOnBackgroundWithLoading {
        val oldEmail = _oldEmail.value
        val newEmail = _newEmail.value
        val password = _password.value
        safeCall { profileChangeEmailUseCase.changeEmail(oldEmail, newEmail, password) }.processingLoading {
            back(profileNavigationListener)
        }
    }

    fun back(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.back(profileNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            //FIREBASE_AUTH_EXCEPTION_CODE to R.string.firebase_auth_exception_message_in_chenge_password_screen,
            EMPTY_PASSWORD_EXCEPTION_CODE to R.string.empty_password_exception_message,
            EMPTY_NEW_EMAIL_EXCEPTION_CODE to R.string.empty_old_email_exception_message,
            EMPTY_NEW_EMAIL_EXCEPTION_CODE to R.string.empty_new_email_exception_message,
            OLD_EMAIL_VALIDATION_EXCEPTION_CODE to R.string.old_email_validation_exception_message,
            NEW_EMAIL_VALIDATION_EXCEPTION_CODE to R.string.new_email_validation_exception_message,
            USER_AVAILABLE_EXCEPTION_CODE to R.string.user_available_exception,
            USER_ABSENCE_EXCEPTION_CODE to R.string.user_absence_exception,
            USER_NOT_AUTHORIZATION_EXCEPTION_CODE to R.string.user_not_authorization_exception_message,
            FIREBASE_NETWORK_EXCEPTION_CODE to R.string.firebase_network_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_PASSWORD_EXCEPTION_CODE to { _passwordTextFieldState.value = TextFieldState.ERROR },
            EMPTY_NEW_EMAIL_EXCEPTION_CODE to { _oldEmailTextFieldState.value = TextFieldState.ERROR },
            EMPTY_NEW_EMAIL_EXCEPTION_CODE to { _newEmailTextFieldState.value = TextFieldState.ERROR },
            OLD_EMAIL_VALIDATION_EXCEPTION_CODE to { _oldEmailTextFieldState.value = TextFieldState.ERROR },
            NEW_EMAIL_VALIDATION_EXCEPTION_CODE to { _newEmailTextFieldState.value = TextFieldState.ERROR },
            USER_AVAILABLE_EXCEPTION_CODE to { _oldEmailTextFieldState.value = TextFieldState.WARNING },
            USER_ABSENCE_EXCEPTION_CODE to { _newEmailTextFieldState.value = TextFieldState.WARNING }
        )
}
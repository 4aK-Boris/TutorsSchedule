package dmitriy.losev.profile.presentation.viewmodels

import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_NETWORK_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.USER_NOT_AUTHORIZATION_EXCEPTION_CODE
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.EMPTY_STRING
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.core.exception.DIFFERENT_PASSWORDS_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.EMPTY_NEW_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.EMPTY_OLD_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.MAX_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.MIN_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.SIMILAR_OLD_AND_NEW_PASSWORDS_EXCEPTION_CODE
import dmitriy.losev.profile.domain.usecases.ProfileAuthUseCase
import dmitriy.losev.profile.domain.usecases.ProfileChangePasswordUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackgroundWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChangePasswordScreenViewModel(
    private val profileChangePasswordUseCase: ProfileChangePasswordUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases,
    private val profileAuthUseCase: ProfileAuthUseCase
) : BaseViewModel() {

    private val _oldPassword = MutableStateFlow(value = EMPTY_STRING)
    private val _newPassword1 = MutableStateFlow(value = EMPTY_STRING)
    private val _newPassword2 = MutableStateFlow(value = EMPTY_STRING)

    private val _oldPasswordTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _newPassword1TextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _newPassword2TextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    private val _oldPasswordVisible = MutableStateFlow(value = false)
    private val _newPassword1Visible = MutableStateFlow(value = false)
    private val _newPassword2Visible = MutableStateFlow(value = false)

    val oldPassword = _oldPassword.asStateFlow()
    val newPassword1 = _newPassword1.asStateFlow()
    val newPassword2 = _newPassword2.asStateFlow()

    val oldPasswordTextFieldState = _oldPasswordTextFieldState.asStateFlow()
    val newPassword1TextFieldState = _newPassword1TextFieldState.asStateFlow()
    val newPassword2TextFieldState = _newPassword2TextFieldState.asStateFlow()

    val oldPasswordVisible = _oldPasswordVisible.asStateFlow()
    val newPassword1Visible = _newPassword1Visible.asStateFlow()
    val newPassword2Visible = _newPassword2Visible.asStateFlow()

    fun onOldPasswordChanged(password: String) {
        _oldPassword.value = password
    }

    fun onNewPassword1Changed(password: String) {
        _newPassword1.value = password
    }

    fun onNewPassword2Changed(password: String) {
        _newPassword2.value = password
    }

    fun onOldPasswordVisibleChanged(passwordVisible: Boolean) {
        _oldPasswordVisible.value = passwordVisible
    }

    fun onNewPassword1VisibleChanged(passwordVisible: Boolean) {
        _newPassword1Visible.value = passwordVisible
    }

    fun onNewPassword2VisibleChanged(passwordVisible: Boolean) {
        _newPassword2Visible.value = passwordVisible
    }

    fun clearOldPasswordTextFieldState() {
        _oldPasswordTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearNewPassword1TextFieldState() {
        _newPassword1TextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearNewPassword2TextFieldState() {
        _newPassword2TextFieldState.value = TextFieldState.DEFAULT
    }

    fun changePassword(profileNavigationListener: ProfileNavigationListener) = runOnBackgroundWithLoading {
        val oldPassword = _oldPassword.value
        val newPassword1 = _newPassword1.value
        val newPassword2 = _newPassword2.value
        safeCall { profileChangePasswordUseCase.changePassword(oldPassword, newPassword1, newPassword2) }.processingLoading(
            exceptionsErrorCode = listOf(FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE, USER_NOT_AUTHORIZATION_EXCEPTION_CODE),
            onError = { profileNavigationUseCases.navigateToAuthenticationScreen(profileNavigationListener) }
        ) {
            back(profileNavigationListener)
        }
    }

    fun back(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.back(profileNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_OLD_PASSWORD_EXCEPTION_CODE to R.string.empty_old_password_exception_message,
            EMPTY_NEW_PASSWORD_EXCEPTION_CODE to R.string.empty_new_password_exception_message,
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.min_length_password_exception_message,
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.max_length_password_exception_message,
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to R.string.password_lower_case_letter_exception_message,
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to R.string.password_upper_case_letter_exception_message,
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to R.string.different_password_exception_message,
            SIMILAR_OLD_AND_NEW_PASSWORDS_EXCEPTION_CODE to R.string.similar_old_and_new_password_exception_message,
            FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE to R.string.firebase_auth_invalid_credentials_exception_message,
            FIREBASE_NETWORK_EXCEPTION_CODE to R.string.firebase_network_exception_message,
            USER_NOT_AUTHORIZATION_EXCEPTION_CODE to R.string.user_not_authorization_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_OLD_PASSWORD_EXCEPTION_CODE to { _oldPasswordTextFieldState.value = TextFieldState.ERROR },
            EMPTY_NEW_PASSWORD_EXCEPTION_CODE to { _newPassword1TextFieldState.value = TextFieldState.ERROR },
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to { _newPassword1TextFieldState.value = TextFieldState.WARNING },
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to { _newPassword1TextFieldState.value = TextFieldState.WARNING },
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to { _newPassword1TextFieldState.value = TextFieldState.WARNING },
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to { _newPassword1TextFieldState.value = TextFieldState.WARNING },
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to {
                _newPassword1TextFieldState.value = TextFieldState.ERROR
                _newPassword2TextFieldState.value = TextFieldState.ERROR
            },
            SIMILAR_OLD_AND_NEW_PASSWORDS_EXCEPTION_CODE to {
                _oldPasswordTextFieldState.value = TextFieldState.WARNING
                _newPassword1TextFieldState.value = TextFieldState.WARNING
            }
        )

}
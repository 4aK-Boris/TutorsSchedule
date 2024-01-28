package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.auth.core.exceptions.DIFFERENT_PASSWORDS_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MAX_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MIN_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationRegistrationUseCase
import dmitriy.losev.firebase.core.exception.FIREBASE_NETWORK_EXCEPTION_CODE
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordScreenViewModel(
    private val authenticationRegistrationUseCase: AuthenticationRegistrationUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : BaseViewModel() {

    private val _password1 = MutableStateFlow(value = EMPTY_STRING)
    private val _password2 = MutableStateFlow(value = EMPTY_STRING)

    private val _password1TextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _password2TextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    private val _password1Visible = MutableStateFlow(value = false)
    private val _password2Visible = MutableStateFlow(value = false)

    val password1 = _password1.asStateFlow()
    val password2 = _password2.asStateFlow()

    val password1TextFieldState = _password1TextFieldState.asStateFlow()
    val password2TextFieldState = _password2TextFieldState.asStateFlow()

    val password1Visible = _password1Visible.asStateFlow()
    val password2Visible = _password2Visible.asStateFlow()

    fun onPassword1Changed(password: String) {
        _password1.value = password
    }

    fun onPassword2Changed(password: String) {
        _password2.value = password
    }

    fun onPassword1VisibleChange(passwordVisible: Boolean) {
        _password1Visible.value = passwordVisible
    }

    fun onPassword2VisibleChange(passwordVisible: Boolean) {
        _password2Visible.value = passwordVisible
    }

    fun clearPassword1TextFieldState() {
        _password1TextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearPassword2TextFieldState() {
        _password2TextFieldState.value = TextFieldState.DEFAULT
    }

    fun registration(
        authenticationNavigationListener: AuthenticationNavigationListener,
        firstName: String?,
        lastName: String?,
        patronymic: String?,
        email: String
    ) = runOnIOWithLoading {
        val password1 = _password1.value
        val password2 = _password2.value
        safeCall {
            authenticationRegistrationUseCase.registration(
                firstName = firstName.toNotNull(),
                lastName = lastName.toNotNull(),
                patronymic = patronymic.toNotNull(),
                email = email,
                password1 = password1,
                password2 = password2
            )
        }.processingLoading {
            authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener)
        }
    }

    fun back(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        authenticationNavigationUseCases.back(authenticationNavigationListener)
    }

    private fun String?.toNotNull(): String = this ?: EMPTY_STRING

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_PASSWORD_EXCEPTION_CODE to R.string.empty_password_exception_message,
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.min_length_password_exception_message,
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to R.string.max_length_password_exception_message,
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to R.string.password_lower_case_letter_exception_message,
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to R.string.password_upper_case_letter_exception_message,
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to R.string.different_password_exception_message,
            FIREBASE_NETWORK_EXCEPTION_CODE to R.string.firebase_network_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_PASSWORD_EXCEPTION_CODE to { _password1TextFieldState.value = TextFieldState.ERROR },
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to { _password1TextFieldState.value = TextFieldState.ERROR },
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to { _password1TextFieldState.value = TextFieldState.ERROR },
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to { _password1TextFieldState.value = TextFieldState.ERROR },
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to { _password1TextFieldState.value = TextFieldState.ERROR },
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to {
                _password1TextFieldState.value = TextFieldState.WARNING
                _password2TextFieldState.value = TextFieldState.WARNING
            }
        )
}
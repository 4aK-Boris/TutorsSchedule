package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.auth.core.exceptions.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.USER_ABSENCE_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.USER_AVAILABLE_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationEmailUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.firebase.core.exception.FIREBASE_NETWORK_EXCEPTION_CODE
import dmitriy.losev.network.exception.CONNECTION_EXCEPTION_CODE
import dmitriy.losev.network.exception.TIMEOUT_EXCEPTION_CODE
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistrationScreenViewModel(
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationEmailUseCase: AuthenticationEmailUseCase
) : BaseViewModel() {

    private val _firstName = MutableStateFlow(value = EMPTY_STRING)
    private val _lastName = MutableStateFlow(value = EMPTY_STRING)
    private val _patronymic = MutableStateFlow(value = EMPTY_STRING)
    private val _email = MutableStateFlow(value = EMPTY_STRING)

    private val _emailTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val patronymic = _patronymic.asStateFlow()
    val email = _email.asStateFlow()

    val emailTextFieldState = _emailTextFieldState.asStateFlow()

    fun onFirstNameChanged(firstName: String) {
        _firstName.value = firstName
    }

    fun onLastNameChanged(lastName: String) {
        _lastName.value = lastName
    }

    fun onPatronymicChanged(patronymic: String) {
        _patronymic.value = patronymic
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun clearEmailTextFieldState() {
        _emailTextFieldState.value = TextFieldState.DEFAULT
    }

    fun onNextButtonClick(authenticationNavigationListener: AuthenticationNavigationListener) = runOnIOWithLoading {
        safeCall { authenticationEmailUseCase.checkEmailValidationForRegistration(email = _email.value) }.processingLoading {
            authenticationNavigationUseCases.navigateToPasswordScreen(
                authenticationNavigationListener = authenticationNavigationListener,
                firstName = _firstName.value,
                lastName = _lastName.value,
                patronymic = _patronymic.value,
                email = _email.value
            )
        }
    }

    fun back(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        authenticationNavigationUseCases.back(authenticationNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            USER_AVAILABLE_EXCEPTION_CODE to R.string.user_available_exception_message,
            USER_ABSENCE_EXCEPTION_CODE to R.string.user_absence_exception_message,
            CONNECTION_EXCEPTION_CODE to R.string.connection_exception_message,
            TIMEOUT_EXCEPTION_CODE to R.string.timeout_exception_code,
            FIREBASE_NETWORK_EXCEPTION_CODE to R.string.firebase_network_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.ERROR },
            EMAIL_VALIDATION_EXCEPTION_CODE to  { _emailTextFieldState.value = TextFieldState.ERROR },
            USER_AVAILABLE_EXCEPTION_CODE to  { _emailTextFieldState.value = TextFieldState.WARNING }
        )
}
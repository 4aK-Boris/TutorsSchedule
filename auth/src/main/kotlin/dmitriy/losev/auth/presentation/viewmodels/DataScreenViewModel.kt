package dmitriy.losev.auth.presentation.viewmodels

import android.net.Uri
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.exceptions.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.USER_AVAILABLE_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.AuthenticationEmailUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.firebase.domain.models.UserDescription
import dmitriy.losev.auth.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataScreenViewModel(
    errorHandler: ErrorHandler,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationEmailUseCase: AuthenticationEmailUseCase
) : BaseViewModel(errorHandler = errorHandler) {


    private val _firstName = MutableStateFlow(value = "")
    private val _lastName = MutableStateFlow(value = "")
    private val _email = MutableStateFlow(value = "")
    private val _uri: MutableStateFlow<Uri?> = MutableStateFlow(value = null)

    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val email = _email.asStateFlow()
    val uri = _uri.asStateFlow()

    fun onUriChanged(uri: Uri?) {
        _uri.value = uri
    }

    fun onFirstNameChanged(firstName: String) {
        _firstName.value = firstName
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onLastNameChanged(lastName: String) {
        _lastName.value = lastName
    }

    fun onNextButtonClick(authenticationNavigationListener: AuthenticationNavigationListener) = runOnBackground {
        val userDescription = UserDescription(firstName = _firstName.value, lastName = _lastName.value, email = _email.value, imageUri = _uri.value)
        authenticationEmailUseCase.checkEmailValidationForRegistration(email = userDescription.email).processing {
            authenticationNavigationUseCases.navigateToPasswordScreen(authenticationNavigationListener, userDescription).processing()
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            USER_AVAILABLE_EXCEPTION_CODE to R.string.user_available_exception_message
        )
}
package dmitriy.losev.auth.presentation.viewmodels

import android.net.Uri
import androidx.navigation.NavController
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.INVALID_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MAX_LENGTH_FIRST_NAME_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MAX_LENGTH_LAST_NAME_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.screens.DataScreenUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataScreenViewModel(
    errorHandler: ErrorHandler,
    private val dataScreenUseCases: DataScreenUseCases
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

    fun onNextButtonClick(navController: NavController) = processing {
        val userDescription = UserDescription(
            firstName = _firstName.value,
            lastName = _lastName.value,
            email = _email.value,
            imageUri = _uri.value
        )
        dataScreenUseCases.navigateToPasswordScreen(
            userDescription = userDescription,
            navController = navController
        )
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to "Введите почту!",
            INVALID_EMAIL_EXCEPTION_CODE to "Введённое значение не является почтой!",
            MAX_LENGTH_FIRST_NAME_EXCEPTION_CODE to "Максимальная длина имени 30 символов!",
            MAX_LENGTH_LAST_NAME_EXCEPTION_CODE to "Максимальная длина фамилии 30 символов!"
        )
}
package dmitriy.losev.auth.presentation.viewmodels

import androidx.navigation.NavController
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.INVALID_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.screens.PasswordResetScreenUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.exception.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordResetScreenViewModel(
    errorHandler: ErrorHandler,
    private val passwordResetScreenUseCases: PasswordResetScreenUseCases
) : BaseViewModel(errorHandler = errorHandler)  {

    private val _email = MutableStateFlow(value = "")

    val email = _email.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun resetPassword(navController: NavController) = processing {
        passwordResetScreenUseCases.resetPassword(email = _email.value, navController = navController)
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to "Введите почту!",
            INVALID_EMAIL_EXCEPTION_CODE to "Введённое значение не является почтой!",
        )
}
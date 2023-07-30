package dmitriy.losev.auth.presentation.viewmodels

import androidx.navigation.NavController
import dmitriy.losev.auth.core.exceptions.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION
import dmitriy.losev.auth.core.exceptions.FIREBASE_AUTH_INVALID_USER_EXCEPTION
import dmitriy.losev.auth.core.exceptions.FIREBASE_TOO_MANY_REQUESTS_EXCEPTION
import dmitriy.losev.auth.core.exceptions.INVALID_EMAIL_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.screens.EmailLoginScreenUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.exception.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmailLoginScreenViewModel(
    errorHandler: ErrorHandler,
    private val emailLoginScreenUseCases: EmailLoginScreenUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _email = MutableStateFlow(value = "")
    private val _password = MutableStateFlow(value = "")

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    fun authWithEmail(
        navController: NavController
    ) = processing {
        emailLoginScreenUseCases.authWithEmail(
            email = _email.value,
            password = _password.value,
            navController = navController
        )
    }

    fun navigateToPasswordResetScreen(navController: NavController) = processing {
            emailLoginScreenUseCases.navigateToPasswordResetScreen(navController = navController, email = _email.value)
        }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to "Введите почту!",
            INVALID_EMAIL_EXCEPTION_CODE to "Введённое значение не является почтой!",
            FIREBASE_AUTH_INVALID_USER_EXCEPTION to "Пользователь не найден!",
            FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION to "Указан неверный пароль!",
            FIREBASE_TOO_MANY_REQUESTS_EXCEPTION to "Слишком много попыток авторизации!"
        )
}
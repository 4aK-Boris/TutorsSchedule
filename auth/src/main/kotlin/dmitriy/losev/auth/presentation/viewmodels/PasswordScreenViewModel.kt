package dmitriy.losev.auth.presentation.viewmodels

import androidx.navigation.NavController
import dmitriy.losev.auth.core.exceptions.DIFFERENT_PASSWORDS_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.EMPTY_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MAX_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.MIN_LENGTH_PASSWORD_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.auth.core.exceptions.PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE
import dmitriy.losev.auth.domain.usecases.screens.PasswordScreenUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordScreenViewModel(
    errorHandler: ErrorHandler,
    private val passwordScreenUseCases: PasswordScreenUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _password1 = MutableStateFlow(value = "")
    private val _password2 = MutableStateFlow(value = "")
    private val _loading = MutableStateFlow(value = false)

    val password1 = _password1.asStateFlow()
    val password2 = _password2.asStateFlow()
    val loading = _loading.asStateFlow()



    fun onPassword1Changed(password: String) {
        _password1.value = password
    }

    fun onPassword2Changed(password: String) {
        _password2.value = password
    }

    fun registration(userDescription: UserDescription, navController: NavController) = processing {
        _loading.value = true
        val result = passwordScreenUseCases.registration(
            userDescription = userDescription,
            navController = navController,
            password1 = _password1.value,
            password2 = _password2.value
        )
        _loading.value = false
        result
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(
            EMPTY_PASSWORD_EXCEPTION_CODE to "Введите пароль!",
            MIN_LENGTH_PASSWORD_EXCEPTION_CODE to "Минимальная длина пароля 8 символов!",
            MAX_LENGTH_PASSWORD_EXCEPTION_CODE to "Максимальная длина пароля 64 символа!",
            PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE to "Пароль должен содержать хотя бы одну строчную букву!",
            PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE to "Пароль должен содержать хотя бы одну заглавную букву!",
            DIFFERENT_PASSWORDS_EXCEPTION_CODE to "Введённые пароли не совпадают!",
            FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE to "Пользователь с такой почтой уже существует!"
        )
}
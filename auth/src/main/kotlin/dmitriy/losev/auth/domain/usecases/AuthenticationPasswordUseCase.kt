package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.repository.PasswordValidationRepository
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result

class AuthenticationPasswordUseCase(
    errorHandler: ErrorHandler,
    private val passwordValidationRepository: PasswordValidationRepository
) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun checkPassword(password1: String, password2: String): Result<Unit> = safeCall {
        passwordValidationRepository.checkPassword(password1, password2)
    }
}
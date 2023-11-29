package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.repository.EmailValidationRepository
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result

class AuthenticationEmailUseCase(
    errorHandler: ErrorHandler,
    private val emailValidationRepository: EmailValidationRepository
) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun checkEmailValidationForRegistration(email: String): Result<Unit> = safeCall {
        emailValidationRepository.checkEmailValidationForRegistration(email)
    }

    suspend fun checkEmailValidationForResetPassword(email: String): Result<Unit> = safeCall {
        emailValidationRepository.checkEmailValidationForResetPassword(email)
    }
}
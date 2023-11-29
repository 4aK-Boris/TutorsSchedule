package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.EmailRepository

class ProfileCheckEmailUseCase(
    errorHandler: ErrorHandler,
    private val emailRepository: EmailRepository
): ProfileBaseUseCase(errorHandler) {

    suspend fun checkEmailForValidation(email: String): Result<Unit> = safeCall {
        emailRepository.checkEmailValidation(email)
    }
}
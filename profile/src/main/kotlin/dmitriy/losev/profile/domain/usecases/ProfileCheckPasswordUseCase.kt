package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.PasswordRepository

class ProfileCheckPasswordUseCase(
    errorHandler: ErrorHandler,
    private val passwordRepository: PasswordRepository
): ProfileBaseUseCase(errorHandler) {

    suspend fun checkPassword(password1: String, password2: String): Result<Unit> = safeCall {
        passwordRepository.checkPassword(password1, password2)
    }
}
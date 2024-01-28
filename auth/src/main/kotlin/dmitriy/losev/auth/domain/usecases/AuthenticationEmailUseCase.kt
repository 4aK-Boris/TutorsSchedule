package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.repository.EmailValidationRepository

class AuthenticationEmailUseCase(private val emailValidationRepository: EmailValidationRepository) : AuthenticationBaseUseCase() {

    suspend fun checkEmailValidationForRegistration(email: String) {
        emailValidationRepository.checkEmailValidationForRegistration(email)
    }

    suspend fun checkEmailValidationForResetPassword(email: String) {
        emailValidationRepository.checkEmailValidationForResetPassword(email)
    }

    suspend fun checkEmailValidationForLogin(email: String) {
        emailValidationRepository.checkEmailForLogin(email)
    }
}
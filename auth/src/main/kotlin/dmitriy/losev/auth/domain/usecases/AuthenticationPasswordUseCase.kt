package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.repository.PasswordValidationRepository

class AuthenticationPasswordUseCase(private val passwordValidationRepository: PasswordValidationRepository) : AuthenticationBaseUseCase() {

    suspend fun checkPassword(password1: String, password2: String) {
        passwordValidationRepository.checkPassword(password1, password2)
    }

    suspend fun checkPassword(password: String) {
        passwordValidationRepository.checkPassword(password)
    }
}
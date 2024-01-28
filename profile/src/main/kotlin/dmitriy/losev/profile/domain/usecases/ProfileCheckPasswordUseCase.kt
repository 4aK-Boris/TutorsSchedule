package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.PasswordRepository

class ProfileCheckPasswordUseCase(private val passwordRepository: PasswordRepository): ProfileBaseUseCase() {

    suspend fun checkPassword(oldPassword: String, newPassword1: String, newPassword2: String) {
        passwordRepository.checkPassword(oldPassword, newPassword1, newPassword2)
    }

    suspend fun checkPassword(password: String) {
        passwordRepository.checkPassword(password)
    }
}
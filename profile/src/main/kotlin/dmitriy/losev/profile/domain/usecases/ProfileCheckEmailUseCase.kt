package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.EmailRepository

class ProfileCheckEmailUseCase(private val emailRepository: EmailRepository): ProfileBaseUseCase() {

    suspend fun checkOldEmailForChange(email: String) {
        emailRepository.checkOldEmailForChange(email)
    }

    suspend fun checkNewEmailForChange(email: String) {
        emailRepository.checkNewEmailForChange(email)
    }
}
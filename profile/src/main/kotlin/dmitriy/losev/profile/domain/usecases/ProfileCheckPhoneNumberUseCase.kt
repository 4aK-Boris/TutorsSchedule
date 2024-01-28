package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.PhoneNumberRepository

class ProfileCheckPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository): ProfileBaseUseCase() {

    suspend fun checkPhoneNumber(phoneNumber: String) {
        phoneNumberRepository.checkPhoneNumber(phoneNumber)
    }
}
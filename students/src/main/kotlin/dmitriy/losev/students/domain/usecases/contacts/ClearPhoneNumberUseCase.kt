package dmitriy.losev.students.domain.usecases.contacts

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.repository.PhoneNumberRepository

class ClearPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) : StudentsBaseUseCase() {

    suspend fun clearPhoneNumber(phoneNumber: String): String{
        return phoneNumberRepository.clearPhoneNumber(phoneNumber)
    }
}
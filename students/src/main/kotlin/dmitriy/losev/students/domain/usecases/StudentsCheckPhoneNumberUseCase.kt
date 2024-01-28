package dmitriy.losev.students.domain.usecases

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.repository.PhoneNumberRepository

class StudentsCheckPhoneNumberUseCase(private val phoneNumberRepository: PhoneNumberRepository) : StudentsBaseUseCase() {

    suspend fun checkPhoneNumber(phoneNumber: String) {
        phoneNumberRepository.checkPhoneNumber(phoneNumber)
    }

    suspend fun checkPhoneNumberForEmpty(phoneNumber: String) {
        phoneNumberRepository.checkPhoneNumberForEmpty(phoneNumber)
    }
}
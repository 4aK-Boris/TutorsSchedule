package dmitriy.losev.students.data.repository

import android.util.Patterns
import dmitriy.losev.students.core.exception.EmptyPhoneNumberException
import dmitriy.losev.students.core.exception.PhoneNumberValidationException
import dmitriy.losev.students.domain.repository.PhoneNumberRepository

class PhoneNumberRepositoryImpl: PhoneNumberRepository {

    override suspend fun clearPhoneNumber(phoneNumber: String): String {
        var newPhoneNumber = phoneNumber.trim()
        if (newPhoneNumber[0] == '8') {
            newPhoneNumber = newPhoneNumber.replaceFirst(oldValue = "8", newValue = "+7")
        }
        newPhoneNumber = newPhoneNumber.replace(oldValue = " ", newValue = "")
        newPhoneNumber = newPhoneNumber.replace(oldValue = "-", newValue = "")
        newPhoneNumber = newPhoneNumber.drop(n = 2)
        return newPhoneNumber
    }

    override suspend fun checkPhoneNumber(phoneNumber: String) {
        if (phoneNumber.isNotBlank() && !Patterns.PHONE.matcher(phoneNumber).matches()) {
            throw PhoneNumberValidationException()
        }
    }

    override suspend fun checkPhoneNumberForEmpty(phoneNumber: String) {
        if (phoneNumber.isBlank()) {
            throw EmptyPhoneNumberException()
        }
    }
}
package dmitriy.losev.profile.data.repositories

import android.util.Patterns
import dmitriy.losev.profile.core.exception.InvalidPhoneNumberException
import dmitriy.losev.profile.domain.repositories.PhoneNumberRepository

class PhoneNumberRepositoryImpl: PhoneNumberRepository {

    override suspend fun checkPhoneNumber(phoneNumber: String) {
        if (phoneNumber.isNotBlank() && !Patterns.PHONE.matcher("+7$phoneNumber").matches()) {
            throw InvalidPhoneNumberException()
        }
    }
}
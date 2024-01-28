package dmitriy.losev.students.domain.repository

interface PhoneNumberRepository {

    suspend fun clearPhoneNumber(phoneNumber: String): String

    suspend fun checkPhoneNumber(phoneNumber: String)

    suspend fun checkPhoneNumberForEmpty(phoneNumber: String)
}
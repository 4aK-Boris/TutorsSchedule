package dmitriy.losev.profile.domain.repositories

interface PhoneNumberRepository {

    suspend fun checkPhoneNumber(phoneNumber: String)
}
package dmitriy.losev.profile.domain.repositories

interface EmailRepository {

    suspend fun checkEmailValidation(email: String)
}
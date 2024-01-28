package dmitriy.losev.profile.domain.repositories

interface EmailRepository {

    suspend fun checkEmail(email: String)

    suspend fun checkOldEmail(email: String)

    suspend fun checkNewEmail(email: String)

    suspend fun checkOldEmailForChange(email: String)

    suspend fun checkNewEmailForChange(email: String)
}
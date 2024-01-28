package dmitriy.losev.profile.domain.repositories

interface PasswordRepository {

    suspend fun checkPassword(oldPassword: String, newPassword1: String, newPassword2: String)

    suspend fun checkPassword(password: String)
}
package dmitriy.losev.profile.domain.repositories

interface PasswordRepository {

    suspend fun checkPassword(password1: String, password2: String)
}
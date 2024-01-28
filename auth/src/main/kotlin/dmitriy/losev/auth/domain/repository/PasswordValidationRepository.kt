package dmitriy.losev.auth.domain.repository

interface PasswordValidationRepository {

    suspend fun checkPassword(password1: String, password2: String)

    suspend fun checkPassword(password: String)
}
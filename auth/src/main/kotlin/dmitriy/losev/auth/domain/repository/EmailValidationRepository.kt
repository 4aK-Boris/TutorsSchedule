package dmitriy.losev.auth.domain.repository

interface EmailValidationRepository {

   suspend fun checkEmailValidationForRegistration(email: String)

   suspend fun checkEmailValidationForResetPassword(email: String)

   suspend fun checkEmailForLogin(email: String)
}
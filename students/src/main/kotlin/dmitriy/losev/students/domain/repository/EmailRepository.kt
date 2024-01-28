package dmitriy.losev.students.domain.repository

interface EmailRepository {

    suspend fun checkEmail(email: String)

    suspend fun checkEmailForEmpty(email: String)
}
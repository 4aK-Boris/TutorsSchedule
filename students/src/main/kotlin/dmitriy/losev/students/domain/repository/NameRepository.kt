package dmitriy.losev.students.domain.repository

interface NameRepository {

    suspend fun checkFirstName(firstName: String)

    suspend fun checkName(name: String)
}
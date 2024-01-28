package dmitriy.losev.subjects.domain.repositories

interface SubjectsNameRepository {

    suspend fun checkSubjectName(name: String)
}
package dmitriy.losev.database.domain.repositories

import dmitriy.losev.core.models.Subject

interface SubjectsRepository {

    suspend fun addSubject(subject: Subject)

    suspend fun updateSubject(subject: Subject)

    suspend fun saveSubject(subject: Subject)

    suspend fun saveSubjects(subjects: List<Subject>)

    suspend fun deleteSubject(subjectId: String)

    suspend fun getSubject(subjectId: String): Subject?

    suspend fun getSubjects(): List<Subject>
}
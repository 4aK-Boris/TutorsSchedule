package dmitriy.losev.subjects.domain.repositories

import dmitriy.losev.core.models.Subject

interface SubjectRepository {

    suspend fun loadSubjectsFromCache(userId: String): List<Subject>?

    suspend fun saveSubjectToCache(userId: String, subjects: List<Subject>)
}
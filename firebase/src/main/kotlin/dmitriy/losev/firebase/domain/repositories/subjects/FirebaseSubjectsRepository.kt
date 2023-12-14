package dmitriy.losev.firebase.domain.repositories.subjects

import dmitriy.losev.firebase.domain.models.Subject

interface FirebaseSubjectsRepository {

    suspend fun addSubject(teacherId: String, subject: Subject): String?

    suspend fun getSubject(teacherId: String, subjectId: String): Subject?

    suspend fun updateSubject(teacherId: String, subjectId: String, subject: Subject)

    suspend fun deleteSubject(teacherId: String, subjectId: String)

    suspend fun getSubjects(teacherId: String): List<Subject>
}
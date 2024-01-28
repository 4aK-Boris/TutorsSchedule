package dmitriy.losev.firebase.data.repositories.subjects

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.core.SUBJECTS
import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import kotlinx.coroutines.tasks.await

class FirebaseSubjectsRepositoryImpl(
    private val reference: DatabaseReference,
    private val subjectMapper: SubjectMapper,
) : FirebaseSubjectsRepository {

    override suspend fun addSubject(teacherId: String, subject: Subject): String? {
        val subjectDTO = subjectMapper.map(value = subject)
        getSubjectsReference(teacherId).push().apply {
            setValue(subjectDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getSubject(teacherId: String, subjectId: String): Subject? {
        return getSubjectsReference(teacherId).child(subjectId).get().await().getValue(SubjectDTO::class.java)?.let { subjectDTO ->
            subjectMapper.map(value = subjectDTO)
        }
    }

    override suspend fun updateSubject(teacherId: String, subjectId: String, subject: Subject) {
        getSubjectsReference(teacherId).updateChildren(mapOf(subjectId to subjectMapper.map(value = subject))).await()
    }

    override suspend fun deleteSubject(teacherId: String, subjectId: String) {
        getSubjectsReference(teacherId).child(subjectId).removeValue().await()
    }

    override suspend fun getSubjects(teacherId: String): List<Subject> {
        return getSubjectsReference(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(SubjectDTO::class.java)?.let { subjectDTO -> subjectMapper.map(value = subjectDTO) }
        }
    }

    private fun getSubjectsReference(teacherId: String): DatabaseReference {
        return reference.child(teacherId).child(SUBJECTS)
    }
}
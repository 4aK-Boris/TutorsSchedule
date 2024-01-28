package dmitriy.losev.firebase.data.repositories.subjects

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.SUBJECTS
import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import kotlinx.coroutines.tasks.await

class FirebaseSubjectsRepositoryImpl(
    reference: DatabaseReference,
    private val subjectMapper: SubjectMapper,
) : FirebaseSubjectsRepository {

    private val subjects by lazy { reference.child(SUBJECTS) }

    override suspend fun addSubject(teacherId: String, subject: Subject): String? {
        val subjectDTO = subjectMapper.map(value = subject)
        subjects.child(teacherId).push().apply {
            setValue(subjectDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getSubject(teacherId: String, subjectId: String): Subject? {
        return subjects.child(teacherId).child(subjectId).get().await().getValue(SubjectDTO::class.java)?.let { subjectDTO ->
            subjectMapper.map(value = subjectDTO)
        }
    }

    override suspend fun updateSubject(teacherId: String, subjectId: String, subject: Subject) {
        subjects.child(teacherId).updateChildren(mapOf(subjectId to subjectMapper.map(value = subject))).await()
    }

    override suspend fun deleteSubject(teacherId: String, subjectId: String) {
        subjects.child(teacherId).child(subjectId).removeValue().await()
    }

    override suspend fun getSubjects(teacherId: String): List<Subject> {
        return subjects.child(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(SubjectDTO::class.java)?.let { subjectDTO -> subjectMapper.map(value = subjectDTO) }
        }
    }
}
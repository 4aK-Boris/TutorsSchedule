package dmitriy.losev.firebase.core.usecases.subjects

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.SUBJECTS
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.firebase.domain.models.Subject
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseSubjectsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val subjectMapper by inject<SubjectMapper>()

    private val subjectsReference by lazy { reference.child(SUBJECTS) }
    private val userUID by lazy { auth.currentUser?.uid ?: throw UserNotAuthorizationException() }

    protected suspend fun addSubject() {
        subjectsReference.child(userUID).child(SUBJECT_ID).setValue(subjectMapper.map(value = subject)).await()
    }

    protected suspend fun addSubject(id: String) {
        subjectsReference.child(userUID).child(id).setValue(subjectMapper.map(value = subject.copy(id = id))).await()
    }

    protected suspend fun deleteSubjects() {
        subjectsReference.child(userUID).get().await().children.forEach { subject ->
            subject.ref.removeValue().await()
        }
    }

    protected suspend fun getSubject(): Subject? {
        return subjectsReference.child(userUID).child(SUBJECT_ID).get().await().getValue(SubjectDTO::class.java)?.let { subjectDTO ->
            subjectMapper.map(value = subjectDTO)
        }
    }

    protected suspend fun getSubject(key: String): Subject? {
        return subjectsReference.child(userUID).child(key).get().await().getValue(SubjectDTO::class.java)?.let { subjectDTO ->
            subjectMapper.map(value = subjectDTO)
        }
    }

    protected suspend fun hasSubject(): Boolean {
        return subjectsReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val SUBJECT_ID = "702yn476f32478n632584c320496732c42"

        const val NAME = "Математика ЕГЭ (профиль)"

        val subject = Subject(id = SUBJECT_ID, name = NAME)
    }
}
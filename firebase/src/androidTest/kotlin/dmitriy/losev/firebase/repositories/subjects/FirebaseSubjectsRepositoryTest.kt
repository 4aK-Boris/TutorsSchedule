package dmitriy.losev.firebase.repositories.subjects

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.SUBJECTS
import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseSubjectsRepositoryTest: BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val subjectMapper by inject<SubjectMapper>()

    private val subjectReference by lazy { reference.child(SUBJECTS) }

    private val firebaseSubjectsRepository by inject<FirebaseSubjectsRepository>()

    override suspend fun tearDown() {
        deleteSubjects()
    }

    @Test
    fun testAddSubject(): Unit = runBlocking {

        val key = firebaseSubjectsRepository.addSubject(userUID, subject)!!

        val hasSubject = hasSubject()

        assertTrue(hasSubject)

        val actualResult = getSubject(key)

        assertEquals(subject.copy(id = key), actualResult)
    }

    @Test
    fun testUpdateSubject(): Unit = runBlocking {

        addSubject()

        val hasSubject = hasSubject()

        assertTrue(hasSubject)

        val newSubject = subject.copy(name = NEW_NAME)

        firebaseSubjectsRepository.updateSubject(userUID, SUBJECT_ID, newSubject)

        val actualResult = getSubject()

        assertEquals(newSubject, actualResult)
    }

    @Test
    fun testDeleteSubject(): Unit = runBlocking {

        addSubject()

        var hasSubject = hasSubject()

        assertTrue(hasSubject)

        firebaseSubjectsRepository.deleteSubject(userUID, SUBJECT_ID)

        hasSubject = hasSubject()

        assertFalse(hasSubject)
    }

    @Test
    fun testGetSubject(): Unit = runBlocking {

        addSubject()

        val hasSubject = hasSubject()

        assertTrue(hasSubject)

        val actualResult = firebaseSubjectsRepository.getSubject(userUID, SUBJECT_ID)

        assertEquals(subject, actualResult)
    }

    @Test
    fun testGetSubjects(): Unit = runBlocking {

        val size = 3

        repeat(size) { index ->
            addSubject(id = "${SUBJECT_ID}$index")
        }

        val actualResult = firebaseSubjectsRepository.getSubjects(userUID)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, Subject ->
            assertEquals("${SUBJECT_ID}$index", Subject.id)
            assertEquals(NAME, Subject.name)
        }
    }


    private suspend fun addSubject() {
        addSubject(id = SUBJECT_ID)
    }

    private suspend fun addSubject(id: String) {
        subjectReference.child(userUID).child(id).setValue(subjectMapper.map(value = subject.copy(id = id))).await()
    }

    private suspend fun deleteSubjects() {
        subjectReference.child(userUID).get().await().children.forEach { dataSnapShot ->
            dataSnapShot.ref.removeValue().await()
        }
    }

    private suspend fun getSubject(): Subject? {
        return getSubject(key = SUBJECT_ID)
    }

    private suspend fun getSubject(key: String): Subject? {
        return subjectReference.child(userUID).child(key).get().await().getValue(SubjectDTO::class.java)?.let { subjectDTO ->
            subjectMapper.map(value = subjectDTO)
        }
    }

    private suspend fun hasSubject(): Boolean {
        return subjectReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val SUBJECT_ID = "4324324324324234v8324324324v32"

        private const val NAME = "Математика ЕГЭ (профиль)"
        private const val NEW_NAME = "Биолгогия ЕГЭ"

        private val subject = Subject(id = SUBJECT_ID, name = NAME)
    }
}
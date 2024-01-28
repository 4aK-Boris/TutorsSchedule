package dmitriy.losev.firebase.repositories.subjects

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.SUBJECTS
import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.firebase.data.repositories.subjects.FirebaseSubjectsRepositoryImpl
import dmitriy.losev.firebase.domain.models.Subject
import io.mockk.Called
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FirebaseSubjectsRepositoryTest {

    private val subjectsReference = mockk<DatabaseReference>()
    private val teacherReference = mockk<DatabaseReference>()
    private val subjectReference = mockk<DatabaseReference>()
    private val subject = mockk<Subject>()
    private val subjectDTO = mockk<SubjectDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val subjectMapper = mockk<SubjectMapper>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseSubjectsRepository = FirebaseSubjectsRepositoryImpl(reference, subjectMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(SUBJECTS) } returns subjectsReference
        every { subjectsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.child(SUBJECT_ID) } returns subjectReference
        every { subjectMapper.map(value = subject) } returns subjectDTO
        every { subjectMapper.map(value = subjectDTO) } returns subject
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddSubject(): Unit = runBlocking {

        every { subjectsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.push() } returns subjectReference
        every { subjectDTO.copy(id = any()) } returns subjectDTO
        every { subjectReference.key } returns KEY
        every { subjectReference.setValue(subjectDTO) } returns task

        firebaseSubjectsRepository.addSubject(TEACHER_ID, subject)

        verifySequence {
            subjectMapper.map(value = subject)
            reference.child(SUBJECTS)
            subjectsReference.child(TEACHER_ID)
            teacherReference.push()
            subjectReference.key
            subjectReference.setValue(subjectDTO)
            subjectReference.key
        }
    }

    @Test
    fun testGetNotNullSubject(): Unit = runBlocking {

        every { subjectReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(SubjectDTO::class.java) } returns subjectDTO

        val actualResult = firebaseSubjectsRepository.getSubject(TEACHER_ID, SUBJECT_ID)

        coVerifySequence {
            reference.child(SUBJECTS)
            subjectsReference.child(TEACHER_ID)
            teacherReference.child(SUBJECT_ID)
            subjectReference.get()
            dataSnapshotResult.getValue(SubjectDTO::class.java)
            subjectMapper.map(value = subjectDTO)
        }

        assertEquals(subject, actualResult)
    }

    @Test
    fun testGetNullableSubject(): Unit = runBlocking {

        every { subjectReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(SubjectDTO::class.java) } returns null

        val actualResult = firebaseSubjectsRepository.getSubject(TEACHER_ID, SUBJECT_ID)

        coVerifySequence {
            reference.child(SUBJECTS)
            subjectsReference.child(TEACHER_ID)
            subjectReference.get()
            dataSnapshotResult.getValue(SubjectDTO::class.java)
        }

        verify { subjectMapper.map(value = subjectDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdateSubject(): Unit = runBlocking {

        every { teacherReference.updateChildren(mapOf(SUBJECT_ID to subjectDTO)) } returns task

        firebaseSubjectsRepository.updateSubject(TEACHER_ID, SUBJECT_ID, subject)

        verifySequence {
            reference.child(SUBJECTS)
            subjectsReference.child(TEACHER_ID)
            subjectMapper.map(value = subject)
            teacherReference.updateChildren(mapOf(SUBJECT_ID to subjectDTO))
        }
    }

    @Test
    fun testDeleteSubject(): Unit = runBlocking {

        every { subjectReference.removeValue() } returns task

        firebaseSubjectsRepository.deleteSubject(TEACHER_ID, SUBJECT_ID)

        verifySequence {
            reference.child(SUBJECTS)
            subjectsReference.child(TEACHER_ID)
            teacherReference.child(SUBJECT_ID)
            subjectReference.removeValue()
        }
    }

    @Test
    fun testGetSubjects(): Unit = runBlocking {

        val listDTO = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { teacherReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listDTO
        every { dataSnapshotResult.getValue(SubjectDTO::class.java) } returns subjectDTO

        val actualResult = firebaseSubjectsRepository.getSubjects(TEACHER_ID)

        verifySequence {
            reference.child(SUBJECTS)
            subjectsReference.child(TEACHER_ID)
            teacherReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(SubjectDTO::class.java)
            subjectMapper.map(value = subjectDTO)
            dataSnapshotResult.getValue(SubjectDTO::class.java)
            subjectMapper.map(value = subjectDTO)
            dataSnapshotResult.getValue(SubjectDTO::class.java)
            subjectMapper.map(value = subjectDTO)
        }

        assertEquals(listDTO.size, actualResult.size)

        actualResult.forEach { subject ->
            assertEquals(subject, subject)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val SUBJECT_ID = "subject_id"
        private const val KEY = "key"
    }
}
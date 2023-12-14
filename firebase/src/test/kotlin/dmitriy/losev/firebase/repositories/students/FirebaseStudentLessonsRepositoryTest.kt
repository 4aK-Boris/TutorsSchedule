package dmitriy.losev.firebase.repositories.students

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentLessonsRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifyOrder
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirebaseStudentLessonsRepositoryTest {

    private val studentLessonsReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val lessonReference = mockk<DatabaseReference>(relaxed = true)
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseStudentLessonRepository = FirebaseStudentLessonsRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(STUDENTS).child(LESSONS) } returns studentLessonsReference
        every { studentLessonsReference.child(STUDENT_ID) } returns studentReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { studentReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns LESSON_ID

        val actualResult = firebaseStudentLessonRepository.getAllLessons(STUDENT_ID)

        verifyOrder {
            reference.child(STUDENTS).child(LESSONS)
            studentLessonsReference.child(STUDENT_ID)
            studentReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(Boolean::class.java)
            dataSnapshotResult.key
        }

        verify(exactly = listSnapshots.size) { dataSnapshotResult.getValue(Boolean::class.java) }
        verify(exactly = listSnapshots.size) { dataSnapshotResult.key }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { lessonId ->
            assertEquals(LESSON_ID, lessonId)
        }
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        every { studentReference.child(LESSON_ID) } returns lessonReference
        every { lessonReference.setValue(true) } returns task

        firebaseStudentLessonRepository.addLesson(STUDENT_ID, LESSON_ID)

        verifySequence {
            reference.child(STUDENTS).child(LESSONS)
            studentLessonsReference.child(STUDENT_ID)
            studentReference.child(LESSON_ID)
            lessonReference.setValue(true)
        }
    }

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        every { studentReference.child(LESSON_ID) } returns lessonReference
        every { lessonReference.removeValue() } returns task

        firebaseStudentLessonRepository.removeLesson(STUDENT_ID, LESSON_ID)

        verifySequence {
            reference.child(STUDENTS).child(LESSONS)
            studentLessonsReference.child(STUDENT_ID)
            studentReference.child(LESSON_ID)
            lessonReference.removeValue()
        }
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        every { studentReference.removeValue() } returns task

        firebaseStudentLessonRepository.removeAllLessons(STUDENT_ID)

        verifySequence {
            reference.child(STUDENTS).child(LESSONS)
            studentLessonsReference.child(STUDENT_ID)
            studentReference.removeValue()
        }
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val LESSON_ID = "lesson_id"
    }
}
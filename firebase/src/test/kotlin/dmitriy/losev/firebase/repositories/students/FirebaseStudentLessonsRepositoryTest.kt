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

    private val studentsReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val lessonsReference = mockk<DatabaseReference>()
    private val lessonReference = mockk<DatabaseReference>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseStudentLessonRepository = FirebaseStudentLessonsRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(STUDENTS) } returns studentsReference
        every { studentsReference.child(STUDENT_ID) } returns studentReference
        every { studentReference.child(LESSONS) } returns lessonsReference
        every { lessonsReference.child(LESSON_ID) } returns lessonReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { lessonsReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns LESSON_ID

        val actualResult = firebaseStudentLessonRepository.getAllLessons(STUDENT_ID)

        verifyOrder {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(LESSONS)
            lessonsReference.get()
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
    fun testGetLimitLessons(): Unit = runBlocking {

        val count = 3

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { lessonsReference.limitToFirst(count) } returns lessonsReference
        every { lessonsReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns LESSON_ID

        val actualResult = firebaseStudentLessonRepository.getLimitLessons(STUDENT_ID, count)

        verifyOrder {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(LESSONS)
            lessonsReference.limitToFirst(count)
            lessonsReference.get()
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

        every { lessonReference.setValue(true) } returns task

        firebaseStudentLessonRepository.addLesson(STUDENT_ID, LESSON_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(LESSONS)
            lessonsReference.child(LESSON_ID)
            lessonReference.setValue(true)
        }
    }

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        every { lessonReference.removeValue() } returns task

        firebaseStudentLessonRepository.removeLesson(STUDENT_ID, LESSON_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(LESSONS)
            lessonsReference.child(LESSON_ID)
            lessonReference.removeValue()
        }
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        every { lessonsReference.removeValue() } returns task

        firebaseStudentLessonRepository.removeAllLessons(STUDENT_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(LESSONS)
            lessonsReference.removeValue()
        }
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val LESSON_ID = "lesson_id"
    }
}
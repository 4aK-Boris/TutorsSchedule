package dmitriy.losev.firebase.repositories.lessons

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.data.dto.LessonDTO
import dmitriy.losev.firebase.data.mappers.LessonMapper
import dmitriy.losev.firebase.data.repositories.lessons.FirebaseLessonsRepositoryImpl
import dmitriy.losev.firebase.domain.models.Lesson
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

class FirebaseLessonsRepositoryTest {

    private val lessonsReference = mockk<DatabaseReference>()
    private val teacherReference = mockk<DatabaseReference>()
    private val lessonReference = mockk<DatabaseReference>()
    private val lesson = mockk<Lesson>()
    private val lessonDTO = mockk<LessonDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val lessonMapper = mockk<LessonMapper>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseLessonsRepository = FirebaseLessonsRepositoryImpl(reference, lessonMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(LESSONS) } returns lessonsReference
        every { lessonsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.child(LESSON_ID) } returns lessonReference
        every { lessonMapper.map(value = lesson) } returns lessonDTO
        every { lessonMapper.map(value = lessonDTO) } returns lesson
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        every { lessonsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.push() } returns lessonReference
        every { lessonDTO.copy(id = any()) } returns lessonDTO
        every { lessonReference.key } returns KEY
        every { lessonReference.setValue(lessonDTO) } returns task

        firebaseLessonsRepository.addLesson(TEACHER_ID, lesson)

        verifySequence {
            lessonMapper.map(value = lesson)
            reference.child(LESSONS)
            lessonsReference.child(TEACHER_ID)
            teacherReference.push()
            lessonReference.key
            lessonReference.setValue(lessonDTO)
            lessonReference.key
        }
    }

    @Test
    fun testGetNotNullLesson(): Unit = runBlocking {

        every { lessonReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(LessonDTO::class.java) } returns lessonDTO

        val actualResult = firebaseLessonsRepository.getLesson(TEACHER_ID, LESSON_ID)

        coVerifySequence {
            reference.child(LESSONS)
            lessonsReference.child(TEACHER_ID)
            teacherReference.child(LESSON_ID)
            lessonReference.get()
            dataSnapshotResult.getValue(LessonDTO::class.java)
            lessonMapper.map(value = lessonDTO)
        }

        assertEquals(lesson, actualResult)
    }

    @Test
    fun testGetNullableLesson(): Unit = runBlocking {

        every { lessonReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(LessonDTO::class.java) } returns null

        val actualResult = firebaseLessonsRepository.getLesson(TEACHER_ID, LESSON_ID)

        coVerifySequence {
            reference.child(LESSONS)
            lessonsReference.child(TEACHER_ID)
            lessonReference.get()
            dataSnapshotResult.getValue(LessonDTO::class.java)
        }

        verify { lessonMapper.map(value = lessonDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdateLesson(): Unit = runBlocking {

        every { teacherReference.updateChildren(mapOf(LESSON_ID to lessonDTO)) } returns task

        firebaseLessonsRepository.updateLesson(TEACHER_ID, LESSON_ID, lesson)

        verifySequence {
            reference.child(LESSONS)
            lessonsReference.child(TEACHER_ID)
            lessonMapper.map(value = lesson)
            teacherReference.updateChildren(mapOf(LESSON_ID to lessonDTO))
        }
    }

    @Test
    fun testDeleteLesson(): Unit = runBlocking {

        every { lessonReference.removeValue() } returns task

        firebaseLessonsRepository.deleteLesson(TEACHER_ID, LESSON_ID)

        verifySequence {
            reference.child(LESSONS)
            lessonsReference.child(TEACHER_ID)
            teacherReference.child(LESSON_ID)
            lessonReference.removeValue()
        }
    }

    @Test
    fun testGetLessons(): Unit = runBlocking {

        val listDTO = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { teacherReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listDTO
        every { dataSnapshotResult.getValue(LessonDTO::class.java) } returns lessonDTO

        val actualResult = firebaseLessonsRepository.getLessons(TEACHER_ID)

        verifySequence {
            reference.child(LESSONS)
            lessonsReference.child(TEACHER_ID)
            teacherReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(LessonDTO::class.java)
            lessonMapper.map(value = lessonDTO)
            dataSnapshotResult.getValue(LessonDTO::class.java)
            lessonMapper.map(value = lessonDTO)
            dataSnapshotResult.getValue(LessonDTO::class.java)
            lessonMapper.map(value = lessonDTO)
        }

        assertEquals(listDTO.size, actualResult.size)

        actualResult.forEach { lesson ->
            assertEquals(lesson, lesson)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val LESSON_ID = "lesson_id"
        private const val KEY = "key"
    }
}
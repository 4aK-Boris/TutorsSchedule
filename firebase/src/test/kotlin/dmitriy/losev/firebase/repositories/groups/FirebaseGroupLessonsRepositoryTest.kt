package dmitriy.losev.firebase.repositories.groups

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupLessonsRepositoryImpl
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

class FirebaseGroupLessonsRepositoryTest {

    private val groupLessonsReference = mockk<DatabaseReference>()
    private val groupReference = mockk<DatabaseReference>()
    private val lessonReference = mockk<DatabaseReference>(relaxed = true)
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseGroupLessonRepository = FirebaseGroupLessonsRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(GROUPS).child(LESSONS) } returns groupLessonsReference
        every { groupLessonsReference.child(GROUP_ID) } returns groupReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { groupReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns LESSON_ID

        val actualResult = firebaseGroupLessonRepository.getAllLessons(GROUP_ID)

        verifyOrder {
            reference.child(GROUPS).child(LESSONS)
            groupLessonsReference.child(GROUP_ID)
            groupReference.get()
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

        every { groupReference.child(LESSON_ID) } returns lessonReference
        every { lessonReference.setValue(true) } returns task

        firebaseGroupLessonRepository.addLesson(GROUP_ID, LESSON_ID)

        verifySequence {
            reference.child(GROUPS).child(LESSONS)
            groupLessonsReference.child(GROUP_ID)
            groupReference.child(LESSON_ID)
            lessonReference.setValue(true)
        }
    }

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        every { groupReference.child(LESSON_ID) } returns lessonReference
        every { lessonReference.removeValue() } returns task

        firebaseGroupLessonRepository.removeLesson(GROUP_ID, LESSON_ID)

        verifySequence {
            reference.child(GROUPS).child(LESSONS)
            groupLessonsReference.child(GROUP_ID)
            groupReference.child(LESSON_ID)
            lessonReference.removeValue()
        }
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        every { groupReference.removeValue() } returns task

        firebaseGroupLessonRepository.removeAllLessons(GROUP_ID)

        verifySequence {
            reference.child(GROUPS).child(LESSONS)
            groupLessonsReference.child(GROUP_ID)
            groupReference.removeValue()
        }
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val LESSON_ID = "lesson_id"
    }
}
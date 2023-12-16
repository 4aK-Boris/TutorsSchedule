package dmitriy.losev.firebase.repositories.lessons

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.data.repositories.lessons.FirebaseLessonPeriodsRepositoryImpl
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

class FirebaseLessonPeriodsRepositoryTest {

    private val lessonsReference = mockk<DatabaseReference>()
    private val lessonReference = mockk<DatabaseReference>()
    private val periodsReference = mockk<DatabaseReference>()
    private val periodReference = mockk<DatabaseReference>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val period = FirebaseTask(data = result)
    private val dataSnapshotPeriod = FirebaseTask(data = dataSnapshotResult)

    private val firebaseLessonPeriodRepository = FirebaseLessonPeriodsRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(LESSONS) } returns lessonsReference
        every { lessonsReference.child(LESSON_ID) } returns lessonReference
        every { lessonReference.child(PERIODS) } returns periodsReference
        every { periodsReference.child(PERIOD_ID) } returns periodReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllPeriods(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { periodsReference.get() } returns dataSnapshotPeriod
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns PERIOD_ID

        val actualResult = firebaseLessonPeriodRepository.getAllPeriods(LESSON_ID)

        verifyOrder {
            reference.child(LESSONS)
            lessonsReference.child(LESSON_ID)
            lessonReference.child(PERIODS)
            periodsReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(Boolean::class.java)
            dataSnapshotResult.key
        }

        verify(exactly = listSnapshots.size) { dataSnapshotResult.getValue(Boolean::class.java) }
        verify(exactly = listSnapshots.size) { dataSnapshotResult.key }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { periodId ->
            assertEquals(PERIOD_ID, periodId)
        }
    }

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        every { periodReference.setValue(true) } returns period

        firebaseLessonPeriodRepository.addPeriod(LESSON_ID, PERIOD_ID)

        verifySequence {
            reference.child(LESSONS)
            lessonsReference.child(LESSON_ID)
            lessonReference.child(PERIODS)
            periodsReference.child(PERIOD_ID)
            periodReference.setValue(true)
        }
    }

    @Test
    fun testRemovePeriod(): Unit = runBlocking {

        every { periodReference.removeValue() } returns period

        firebaseLessonPeriodRepository.removePeriod(LESSON_ID, PERIOD_ID)

        verifySequence {
            reference.child(LESSONS)
            lessonsReference.child(LESSON_ID)
            lessonReference.child(PERIODS)
            periodsReference.child(PERIOD_ID)
            periodReference.removeValue()
        }
    }

    @Test
    fun removeAllPeriods(): Unit = runBlocking {

        every { periodsReference.removeValue() } returns period

        firebaseLessonPeriodRepository.removeAllPeriods(LESSON_ID)

        verifySequence {
            reference.child(LESSONS)
            lessonsReference.child(LESSON_ID)
            lessonReference.child(PERIODS)
            periodsReference.removeValue()
        }
    }

    companion object {

        private const val LESSON_ID = "lesson_id"
        private const val PERIOD_ID = "period_id"
    }
}
package dmitriy.losev.firebase.repositories.periods

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.repositories.periods.FirebasePeriodTasksRepositoryImpl
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

class FirebasePeriodTasksRepositoryTest {

    private val periodsReference = mockk<DatabaseReference>()
    private val periodReference = mockk<DatabaseReference>()
    private val tasksReference = mockk<DatabaseReference>()
    private val taskReference = mockk<DatabaseReference>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebasePeriodTaskRepository = FirebasePeriodTasksRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(PERIODS) } returns periodsReference
        every { periodsReference.child(PERIOD_ID) } returns periodReference
        every { periodReference.child(TASKS) } returns tasksReference
        every { tasksReference.child(TASK_ID) } returns taskReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { tasksReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns TASK_ID

        val actualResult = firebasePeriodTaskRepository.getAllTasks(PERIOD_ID)

        verifyOrder {
            reference.child(PERIODS)
            periodsReference.child(PERIOD_ID)
            periodReference.child(TASKS)
            tasksReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(Boolean::class.java)
            dataSnapshotResult.key
        }

        verify(exactly = listSnapshots.size) { dataSnapshotResult.getValue(Boolean::class.java) }
        verify(exactly = listSnapshots.size) { dataSnapshotResult.key }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { taskId ->
            assertEquals(TASK_ID, taskId)
        }
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        every { taskReference.setValue(true) } returns task

        firebasePeriodTaskRepository.addTask(PERIOD_ID, TASK_ID)

        verifySequence {
            reference.child(PERIODS)
            periodsReference.child(PERIOD_ID)
            periodReference.child(TASKS)
            tasksReference.child(TASK_ID)
            taskReference.setValue(true)
        }
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        every { taskReference.removeValue() } returns task

        firebasePeriodTaskRepository.removeTask(PERIOD_ID, TASK_ID)

        verifySequence {
            reference.child(PERIODS)
            periodsReference.child(PERIOD_ID)
            periodReference.child(TASKS)
            tasksReference.child(TASK_ID)
            taskReference.removeValue()
        }
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        every { tasksReference.removeValue() } returns task

        firebasePeriodTaskRepository.removeAllTasks(PERIOD_ID)

        verifySequence {
            reference.child(PERIODS)
            periodsReference.child(PERIOD_ID)
            periodReference.child(TASKS)
            tasksReference.removeValue()
        }
    }

    companion object {

        private const val PERIOD_ID = "period_id"
        private const val TASK_ID = "task_id"
    }
}
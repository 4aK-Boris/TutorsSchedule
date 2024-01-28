package dmitriy.losev.firebase.repositories.groups

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupTasksRepositoryImpl
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

class FirebaseGroupTasksRepositoryTest {

    private val groupsReference = mockk<DatabaseReference>()
    private val groupReference = mockk<DatabaseReference>()
    private val tasksReference = mockk<DatabaseReference>()
    private val taskReference = mockk<DatabaseReference>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseGroupTaskRepository = FirebaseGroupTasksRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(GROUPS) } returns groupsReference
        every { groupsReference.child(GROUP_ID) } returns groupReference
        every { groupReference.child(TASKS) } returns tasksReference
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

        val actualResult = firebaseGroupTaskRepository.getAllTasks(GROUP_ID)

        verifyOrder {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(TASKS)
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

        firebaseGroupTaskRepository.addTask(GROUP_ID, TASK_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(TASKS)
            tasksReference.child(TASK_ID)
            taskReference.setValue(true)
        }
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        every { taskReference.removeValue() } returns task

        firebaseGroupTaskRepository.removeTask(GROUP_ID, TASK_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(TASKS)
            tasksReference.child(TASK_ID)
            taskReference.removeValue()
        }
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        every { tasksReference.removeValue() } returns task

        firebaseGroupTaskRepository.removeAllTasks(GROUP_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(TASKS)
            tasksReference.removeValue()
        }
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val TASK_ID = "task_id"
    }
}
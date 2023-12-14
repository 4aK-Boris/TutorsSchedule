package dmitriy.losev.firebase.repositories.tasks

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.data.repositories.task.FirebaseTasksRepositoryImpl
import dmitriy.losev.firebase.domain.models.Task
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

class FirebaseTasksRepositoryTest {

    private val tasksReference = mockk<DatabaseReference>()
    private val teacherReference = mockk<DatabaseReference>()
    private val taskReference = mockk<DatabaseReference>()
    private val task = mockk<Task>()
    private val taskDTO = mockk<TaskDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val taskMapper = mockk<TaskMapper>()

    private val firebaseTask = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseTasksRepository = FirebaseTasksRepositoryImpl(reference, taskMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(TASKS) } returns tasksReference
        every { tasksReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.child(TASK_ID) } returns taskReference
        every { taskMapper.map(value = task) } returns taskDTO
        every { taskMapper.map(value = taskDTO) } returns task
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        every { tasksReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.push() } returns taskReference
        every { taskDTO.copy(id = any()) } returns taskDTO
        every { taskReference.key } returns KEY
        every { taskReference.setValue(taskDTO) } returns firebaseTask

        firebaseTasksRepository.addTask(TEACHER_ID, task)

        verifySequence {
            taskMapper.map(value = task)
            reference.child(TASKS)
            tasksReference.child(TEACHER_ID)
            teacherReference.push()
            taskReference.key
            taskReference.setValue(taskDTO)
            taskReference.key
        }
    }

    @Test
    fun testGetNotNullTask(): Unit = runBlocking {

        every { taskReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(TaskDTO::class.java) } returns taskDTO

        val actualResult = firebaseTasksRepository.getTask(TEACHER_ID, TASK_ID)

        coVerifySequence {
            reference.child(TASKS)
            tasksReference.child(TEACHER_ID)
            teacherReference.child(TASK_ID)
            taskReference.get()
            dataSnapshotResult.getValue(TaskDTO::class.java)
            taskMapper.map(value = taskDTO)
        }

        assertEquals(task, actualResult)
    }

    @Test
    fun testGetNullableTask(): Unit = runBlocking {

        every { taskReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(TaskDTO::class.java) } returns null

        val actualResult = firebaseTasksRepository.getTask(TEACHER_ID, TASK_ID)

        coVerifySequence {
            reference.child(TASKS)
            tasksReference.child(TEACHER_ID)
            taskReference.get()
            dataSnapshotResult.getValue(TaskDTO::class.java)
        }

        verify { taskMapper.map(value = taskDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdateTask(): Unit = runBlocking {

        every { teacherReference.updateChildren(mapOf(TASK_ID to taskDTO)) } returns firebaseTask

        firebaseTasksRepository.updateTask(TEACHER_ID, TASK_ID, task)

        verifySequence {
            reference.child(TASKS)
            tasksReference.child(TEACHER_ID)
            taskMapper.map(value = task)
            teacherReference.updateChildren(mapOf(TASK_ID to taskDTO))
        }
    }

    @Test
    fun testDeleteTask(): Unit = runBlocking {

        every { taskReference.removeValue() } returns firebaseTask

        firebaseTasksRepository.deleteTask(TEACHER_ID, TASK_ID)

        verifySequence {
            reference.child(TASKS)
            tasksReference.child(TEACHER_ID)
            teacherReference.child(TASK_ID)
            taskReference.removeValue()
        }
    }

    @Test
    fun testGetTasks(): Unit = runBlocking {

        val listDTO = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { teacherReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listDTO
        every { dataSnapshotResult.getValue(TaskDTO::class.java) } returns taskDTO

        val actualResult = firebaseTasksRepository.getTasks(TEACHER_ID)

        verifySequence {
            reference.child(TASKS)
            tasksReference.child(TEACHER_ID)
            teacherReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(TaskDTO::class.java)
            taskMapper.map(value = taskDTO)
            dataSnapshotResult.getValue(TaskDTO::class.java)
            taskMapper.map(value = taskDTO)
            dataSnapshotResult.getValue(TaskDTO::class.java)
            taskMapper.map(value = taskDTO)
        }

        assertEquals(listDTO.size, actualResult.size)

        actualResult.forEach { task ->
            assertEquals(task, task)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val TASK_ID = "task_id"
        private const val KEY = "key"
    }
}
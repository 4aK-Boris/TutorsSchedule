package dmitriy.losev.firebase.repositories.students

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentTasksRepositoryImpl
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

class FirebaseStudentTasksRepositoryTest {

    private val studentsReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val tasksReference = mockk<DatabaseReference>()
    private val taskReference = mockk<DatabaseReference>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseStudentTaskRepository = FirebaseStudentTasksRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(STUDENTS) } returns studentsReference
        every { studentsReference.child(STUDENT_ID) } returns studentReference
        every { studentReference.child(TASKS) } returns tasksReference
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

        val actualResult = firebaseStudentTaskRepository.getAllTasks(STUDENT_ID)

        verifyOrder {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(TASKS)
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
    fun testGetLimitTasks(): Unit = runBlocking {

        val count = 3

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { tasksReference.limitToFirst(count) } returns tasksReference
        every { tasksReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns TASK_ID

        val actualResult = firebaseStudentTaskRepository.getLimitTasks(STUDENT_ID, count)

        verifyOrder {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(TASKS)
            tasksReference.limitToFirst(count)
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

        firebaseStudentTaskRepository.addTask(STUDENT_ID, TASK_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(TASKS)
            tasksReference.child(TASK_ID)
            taskReference.setValue(true)
        }
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        every { taskReference.removeValue() } returns task

        firebaseStudentTaskRepository.removeTask(STUDENT_ID, TASK_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(TASKS)
            tasksReference.child(TASK_ID)
            taskReference.removeValue()
        }
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        every { tasksReference.removeValue() } returns task

        firebaseStudentTaskRepository.removeAllTasks(STUDENT_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.child(TASKS)
            tasksReference.removeValue()
        }
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val TASK_ID = "task_id"
    }
}
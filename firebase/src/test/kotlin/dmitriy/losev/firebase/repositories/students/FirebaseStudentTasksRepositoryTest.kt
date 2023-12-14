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

    private val studentTasksReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val taskReference = mockk<DatabaseReference>(relaxed = true)
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseStudentTaskRepository = FirebaseStudentTasksRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(STUDENTS).child(TASKS) } returns studentTasksReference
        every { studentTasksReference.child(STUDENT_ID) } returns studentReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { studentReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns TASK_ID

        val actualResult = firebaseStudentTaskRepository.getAllTasks(STUDENT_ID)

        verifyOrder {
            reference.child(STUDENTS).child(TASKS)
            studentTasksReference.child(STUDENT_ID)
            studentReference.get()
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

        every { studentReference.child(TASK_ID) } returns taskReference
        every { taskReference.setValue(true) } returns task

        firebaseStudentTaskRepository.addTask(STUDENT_ID, TASK_ID)

        verifySequence {
            reference.child(STUDENTS).child(TASKS)
            studentTasksReference.child(STUDENT_ID)
            studentReference.child(TASK_ID)
            taskReference.setValue(true)
        }
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        every { studentReference.child(TASK_ID) } returns taskReference
        every { taskReference.removeValue() } returns task

        firebaseStudentTaskRepository.removeTask(STUDENT_ID, TASK_ID)

        verifySequence {
            reference.child(STUDENTS).child(TASKS)
            studentTasksReference.child(STUDENT_ID)
            studentReference.child(TASK_ID)
            taskReference.removeValue()
        }
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        every { studentReference.removeValue() } returns task

        firebaseStudentTaskRepository.removeAllTasks(STUDENT_ID)

        verifySequence {
            reference.child(STUDENTS).child(TASKS)
            studentTasksReference.child(STUDENT_ID)
            studentReference.removeValue()
        }
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val TASK_ID = "task_id"
    }
}
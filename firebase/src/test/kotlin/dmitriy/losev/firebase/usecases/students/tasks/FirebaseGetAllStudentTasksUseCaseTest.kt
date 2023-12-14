package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseGetAllStudentTasksUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllStudentTasksUseCaseTest {

    private val firebaseStudentTasksRepository = mockk<FirebaseStudentTasksRepository>()

    private val firebaseGetAllStudentTasksUseCase = FirebaseGetAllStudentTasksUseCase(firebaseStudentTasksRepository)

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val tasks = listOf(TASK_ID, TASK_ID, TASK_ID)

        coEvery { firebaseStudentTasksRepository.getAllTasks(STUDENT_ID) } returns tasks

        val actualResult = firebaseGetAllStudentTasksUseCase.getAllTasks(STUDENT_ID)

        coVerify { firebaseStudentTasksRepository.getAllTasks(STUDENT_ID) }

        assertContentEquals(tasks, actualResult)
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val TASK_ID = "task_id"
    }
}
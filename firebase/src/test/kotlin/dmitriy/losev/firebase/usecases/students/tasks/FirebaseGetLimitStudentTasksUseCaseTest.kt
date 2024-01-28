package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseGetLimitStudentTasksUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetLimitStudentTasksUseCaseTest {

    private val firebaseStudentTasksRepository = mockk<FirebaseStudentTasksRepository>()

    private val firebaseGetLimitStudentTasksUseCase = FirebaseGetLimitStudentTasksUseCase(firebaseStudentTasksRepository)

    @Test
    fun testGetLimitTasks(): Unit = runBlocking {

        val count = 3

        val tasks = listOf(TASK_ID, TASK_ID, TASK_ID)

        coEvery { firebaseStudentTasksRepository.getLimitTasks(STUDENT_ID, count) } returns tasks

        val actualResult = firebaseGetLimitStudentTasksUseCase.getLimitTasks(STUDENT_ID, count)

        coVerify { firebaseStudentTasksRepository.getLimitTasks(STUDENT_ID, count) }

        assertContentEquals(tasks, actualResult)
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val TASK_ID = "task_id"
    }
}
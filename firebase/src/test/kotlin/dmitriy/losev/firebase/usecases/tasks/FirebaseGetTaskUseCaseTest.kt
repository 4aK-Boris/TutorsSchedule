package dmitriy.losev.firebase.usecases.tasks

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.exception.NullableTaskException
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseGetTaskUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetTaskUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val task = mockk<Task>()

    private val firebaseTasksRepository = mockk<FirebaseTasksRepository>()
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetTaskUseCase = FirebaseGetTaskUseCase(firebaseTasksRepository, firebaseGetUseCase)

    @Test
    fun testGetNotNullTask(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseTasksRepository.getTask(teacherId = TEACHER_ID, taskId = TASK_ID) } returns task
        every { user.uid } returns TEACHER_ID

        val actualResult = firebaseGetTaskUseCase.getTask(TASK_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseTasksRepository.getTask(teacherId = TEACHER_ID, taskId = TASK_ID)
        }

        assertEquals(task, actualResult)
    }

    @Test
    fun testGetNullableTask(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseTasksRepository.getTask(teacherId = TEACHER_ID, taskId = TASK_ID) } returns null
        every { user.uid } returns TEACHER_ID

        assertFailsWith(exceptionClass = NullableTaskException::class) {
            firebaseGetTaskUseCase.getTask(TASK_ID)
        }

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseTasksRepository.getTask(teacherId = TEACHER_ID, taskId = TASK_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val TASK_ID = "task_id"
    }
}
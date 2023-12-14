package dmitriy.losev.firebase.usecases.tasks

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseDeleteTaskUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteTaskUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseTasksRepository = mockk<FirebaseTasksRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseDeleteTaskUseCase = FirebaseDeleteTaskUseCase(firebaseTasksRepository, firebaseGetUseCase)

    @Test
    fun testDeleteTask(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseDeleteTaskUseCase.deleteTask(TASK_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseTasksRepository.deleteTask(TEACHER_ID, TASK_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val TASK_ID = "task_id"
    }
}
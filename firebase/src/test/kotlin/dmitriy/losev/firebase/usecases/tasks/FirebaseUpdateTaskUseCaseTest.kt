package dmitriy.losev.firebase.usecases.tasks

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseUpdateTaskUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdateTaskUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val task = mockk<Task>()

    private val firebaseTasksRepository = mockk<FirebaseTasksRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseUpdateTaskUseCase = FirebaseUpdateTaskUseCase(firebaseTasksRepository, firebaseGetUseCase)

    @Test
    fun testUpdateTask(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseUpdateTaskUseCase.updateTask(TASK_ID, task)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseTasksRepository.updateTask(TEACHER_ID, TASK_ID, task)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val TASK_ID = "task_id"
    }
}
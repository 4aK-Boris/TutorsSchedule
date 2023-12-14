package dmitriy.losev.firebase.usecases.tasks

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseAddTaskUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddTaskUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val task = mockk<Task>()

    private val firebaseTasksRepository = mockk<FirebaseTasksRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseAddTaskUseCase = FirebaseAddTaskUseCase(firebaseTasksRepository, firebaseGetUseCase)

    @Test
    fun testAddTask(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseAddTaskUseCase.addTask(task)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseTasksRepository.addTask(TEACHER_ID, any())
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}
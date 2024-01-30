package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseGetAllGroupTasksUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllGroupTasksUseCaseTest {

    private val firebaseGroupTasksRepository = mockk<FirebaseGroupTasksRepository>()

    private val firebaseGetAllGroupTasksUseCase = FirebaseGetAllGroupTasksUseCase(firebaseGroupTasksRepository)

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val tasks = listOf(TASK_ID, TASK_ID, TASK_ID)

        coEvery { firebaseGroupTasksRepository.getTasks(GROUP_ID) } returns tasks

        val actualResult = firebaseGetAllGroupTasksUseCase.getAllTasks(GROUP_ID)

        coVerify { firebaseGroupTasksRepository.getTasks(GROUP_ID) }

        assertContentEquals(tasks, actualResult)
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val TASK_ID = "task_id"
    }
}
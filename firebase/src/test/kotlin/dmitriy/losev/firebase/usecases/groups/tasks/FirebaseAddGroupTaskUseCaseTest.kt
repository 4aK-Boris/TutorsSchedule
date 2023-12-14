package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseAddGroupTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddGroupTaskUseCaseTest {

    private val firebaseGroupTasksRepository = mockk<FirebaseGroupTasksRepository>(relaxed = true)

    private val firebaseAddGroupTaskUseCase = FirebaseAddGroupTaskUseCase(firebaseGroupTasksRepository)

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddGroupTaskUseCase.addTask(GROUP_ID, TASK_ID)

        coVerify { firebaseGroupTasksRepository.addTask(GROUP_ID, TASK_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
        private const val TASK_ID = "task_id"
    }
}
package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseRemoveAllGroupTasksUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllGroupTasksUseCaseTest {

    private val firebaseGroupTasksRepository = mockk<FirebaseGroupTasksRepository>(relaxed = true)

    private val firebaseRemoveAllGroupTasksUseCase = FirebaseRemoveAllGroupTasksUseCase(firebaseGroupTasksRepository)

    @Test
    fun testRemoveAllTasks(): Unit = runBlocking {

        firebaseRemoveAllGroupTasksUseCase.removeAllTasks(GROUP_ID)

        coVerify { firebaseGroupTasksRepository.removeAllTasks(GROUP_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
    }
}
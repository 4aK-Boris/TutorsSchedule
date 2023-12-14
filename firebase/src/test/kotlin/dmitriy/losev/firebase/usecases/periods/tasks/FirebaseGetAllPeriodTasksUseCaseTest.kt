package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseGetAllPeriodTasksUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllPeriodTasksUseCaseTest {

    private val firebasePeriodTasksRepository = mockk<FirebasePeriodTasksRepository>()

    private val firebaseGetAllPeriodTasksUseCase = FirebaseGetAllPeriodTasksUseCase(firebasePeriodTasksRepository)

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val tasks = listOf(TASK_ID, TASK_ID, TASK_ID)

        coEvery { firebasePeriodTasksRepository.getAllTasks(PERIOD_ID) } returns tasks

        val actualResult = firebaseGetAllPeriodTasksUseCase.getAllTasks(PERIOD_ID)

        coVerify { firebasePeriodTasksRepository.getAllTasks(PERIOD_ID) }

        assertContentEquals(tasks, actualResult)
    }

    companion object {

        private const val PERIOD_ID = "period_id"
        private const val TASK_ID = "task_id"
    }
}
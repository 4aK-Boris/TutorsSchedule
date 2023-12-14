package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseRemovePeriodTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemovePeriodTaskUseCaseTest {

    private val firebasePeriodTasksRepository = mockk<FirebasePeriodTasksRepository>(relaxed = true)

    private val firebaseRemovePeriodTaskUseCase = FirebaseRemovePeriodTaskUseCase(firebasePeriodTasksRepository)

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        firebaseRemovePeriodTaskUseCase.removeTask(PERIOD_ID, TASK_ID)

        coVerify { firebasePeriodTasksRepository.removeTask(PERIOD_ID, TASK_ID) }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
        private const val TASK_ID = "task_id"
    }
}
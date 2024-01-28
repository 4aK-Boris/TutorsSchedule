package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseRemoveAllPeriodTasksUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllPeriodTasksUseCaseTest {

    private val firebasePeriodTasksRepository = mockk<FirebasePeriodTasksRepository>(relaxed = true)

    private val firebaseRemoveAllPeriodTasksUseCase = FirebaseRemoveAllPeriodTasksUseCase(firebasePeriodTasksRepository)

    @Test
    fun testRemoveAllTasks(): Unit = runBlocking {

        firebaseRemoveAllPeriodTasksUseCase.removeAllTasks(PERIOD_ID)

        coVerify { firebasePeriodTasksRepository.removeAllTasks(PERIOD_ID) }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
    }
}
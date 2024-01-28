package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseAddPeriodTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddPeriodTaskUseCaseTest {

    private val firebasePeriodTasksRepository = mockk<FirebasePeriodTasksRepository>(relaxed = true)

    private val firebaseAddPeriodTaskUseCase = FirebaseAddPeriodTaskUseCase(firebasePeriodTasksRepository)

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddPeriodTaskUseCase.addTask(PERIOD_ID, TASK_ID)

        coVerify { firebasePeriodTasksRepository.addTask(PERIOD_ID, TASK_ID) }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
        private const val TASK_ID = "task_id"
    }
}
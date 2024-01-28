package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeleteFullPeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeletePeriodUseCase
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseGetAllPeriodTasksUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseDeleteTaskUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteFullPeriodUseCaseTest {

    private val firebaseGetAllPeriodTasksUseCase = mockk<FirebaseGetAllPeriodTasksUseCase>()
    private val firebaseDeletePeriodUseCase = mockk<FirebaseDeletePeriodUseCase>(relaxed = true)
    private val firebaseDeleteTaskUseCase = mockk<FirebaseDeleteTaskUseCase>(relaxed = true)

    private val tasks = listOf(TASK_ID, TASK_ID, TASK_ID)

    private val firebaseDeleteFullPeriodUseCase = FirebaseDeleteFullPeriodUseCase(
        firebaseGetAllPeriodTasksUseCase,
        firebaseDeletePeriodUseCase,
        firebaseDeleteTaskUseCase
    )

    @Test
    fun testDeleteFullPeriod(): Unit = runBlocking {

        coEvery { firebaseGetAllPeriodTasksUseCase.getAllTasks(PERIOD_ID) } returns tasks

        firebaseDeleteFullPeriodUseCase.deleteFullPeriod(PERIOD_ID)

        coVerify { firebaseGetAllPeriodTasksUseCase.getAllTasks(PERIOD_ID) }
        coVerify(exactly = SIZE) { firebaseDeleteTaskUseCase.deleteTask(TASK_ID) }
        coVerify { firebaseDeletePeriodUseCase.deletePeriod(PERIOD_ID) }
    }

    companion object {

        private const val SIZE = 3

        private const val PERIOD_ID = "period_id"
        private const val TASK_ID = "task_id"
    }
}
package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseGetAllPeriodTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllPeriodTasksUseCaseTest: BasePeriodTasksUseCaseTest() {

    private val firebaseGetAllPeriodTasksUseCase by inject<FirebaseGetAllPeriodTasksUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count = count)

        val actualResult = firebaseGetAllPeriodTasksUseCase.getAllTasks(PERIOD_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, taskId ->
            assertEquals("${TASK_ID}-$index", taskId)
        }
    }
}
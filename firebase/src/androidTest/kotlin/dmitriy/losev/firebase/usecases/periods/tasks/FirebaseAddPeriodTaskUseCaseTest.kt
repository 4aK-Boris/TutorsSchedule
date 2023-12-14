package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseAddPeriodTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddPeriodTaskUseCaseTest: BasePeriodTasksUseCaseTest() {

    private val firebaseAddPeriodTaskUseCase by inject<FirebaseAddPeriodTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddPeriodTaskUseCase.addTask(PERIOD_ID, TASK_ID)

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask()

        assertEquals(TASK_ID, actualResult)
    }
}
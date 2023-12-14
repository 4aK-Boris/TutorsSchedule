package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseRemovePeriodTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemovePeriodTaskUseCaseTest: BasePeriodTasksUseCaseTest() {

    private val firebaseRemovePeriodTaskUseCase by inject<FirebaseRemovePeriodTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        addTask()

        var hasTask = hasTask()

        assertTrue(hasTask)

        firebaseRemovePeriodTaskUseCase.removeTask(PERIOD_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }
}
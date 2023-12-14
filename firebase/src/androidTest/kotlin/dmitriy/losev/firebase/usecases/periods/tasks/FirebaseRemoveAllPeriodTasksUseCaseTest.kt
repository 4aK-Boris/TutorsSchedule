package dmitriy.losev.firebase.usecases.periods.tasks

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseRemoveAllPeriodTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllPeriodTasksUseCaseTest: BasePeriodTasksUseCaseTest() {

    private val firebaseRemoveAllPeriodTasksUseCase by inject<FirebaseRemoveAllPeriodTasksUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count)

        var hasTasks = hasTasks()

        assertTrue(hasTasks)

        firebaseRemoveAllPeriodTasksUseCase.removeAllTasks(PERIOD_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }
}
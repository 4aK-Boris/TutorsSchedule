package dmitriy.losev.firebase.usecases.tasks

import dmitriy.losev.firebase.core.usecases.tasks.BaseTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseDeleteTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseDeleteTaskUseCaseTest: BaseTasksUseCaseTest() {

    private val firebaseDeleteTaskUseCase by inject<FirebaseDeleteTaskUseCase>()

    override suspend fun setUp() {
        logIn()
        addTask()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteTask(): Unit = runBlocking {

        var hasTask =  hasTask()

        assertTrue(hasTask)

        firebaseDeleteTaskUseCase.deleteTask(TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }
}
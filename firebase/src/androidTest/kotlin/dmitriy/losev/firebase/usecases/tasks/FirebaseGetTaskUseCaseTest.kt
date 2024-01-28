package dmitriy.losev.firebase.usecases.tasks

import dmitriy.losev.firebase.core.usecases.tasks.BaseTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseGetTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class FirebaseGetTaskUseCaseTest: BaseTasksUseCaseTest() {

    private val firebaseGetTaskUseCase by inject<FirebaseGetTaskUseCase>()

    override suspend fun setUp() {
        logIn()
        addTask()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testGetTask(): Unit = runBlocking {

        val hasTask =  hasTask()

        assertTrue(hasTask)

        val actualResult = firebaseGetTaskUseCase.getTask(TASK_ID)

        equalsTasks(task, actualResult)
    }
}
package dmitriy.losev.firebase.usecases.tasks

import dmitriy.losev.firebase.core.usecases.tasks.BaseTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseAddTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class FirebaseAddTaskUseCaseTest: BaseTasksUseCaseTest() {

    private val firebaseAddTaskUseCase by inject<FirebaseAddTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        val key = firebaseAddTaskUseCase.addTask(task)

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask(key)

        equalsTasks(task.copy(id = key), actualResult)
    }
}
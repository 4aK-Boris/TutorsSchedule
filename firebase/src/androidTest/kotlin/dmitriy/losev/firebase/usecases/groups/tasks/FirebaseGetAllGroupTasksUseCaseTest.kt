package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseGetAllGroupTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllGroupTasksUseCaseTest: BaseGroupTasksUseCaseTest() {

    private val firebaseGetAllGroupTasksUseCase by inject<FirebaseGetAllGroupTasksUseCase>()

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

        val actualResult = firebaseGetAllGroupTasksUseCase.getAllTasks(GROUP_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, taskId ->
            assertEquals("${TASK_ID}-$index", taskId)
        }
    }
}
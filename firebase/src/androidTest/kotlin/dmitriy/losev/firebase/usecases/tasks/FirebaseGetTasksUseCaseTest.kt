package dmitriy.losev.firebase.usecases.tasks

import dmitriy.losev.firebase.core.usecases.tasks.BaseTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseGetTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetTasksUseCaseTest : BaseTasksUseCaseTest() {

    private val firebaseGetSimpleTasksUseCase by inject<FirebaseGetTasksUseCase>()

    override suspend fun setUp() {
        logIn()
        addTask(ID_1)
        addTask(ID_2)
        addTask(ID_3)
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testGetTasks(): Unit = runBlocking {

        val actualResult = firebaseGetSimpleTasksUseCase.getTasks()

        assertEquals(SIZE, actualResult.size)

        actualResult.forEachIndexed { index, otherTask ->
            equalsTasks(task.copy(id = "id_${index + 1}"), otherTask)
        }
    }

    companion object {
        private const val SIZE = 3

        private const val ID_1 = "id_1"
        private const val ID_2 = "id_2"
        private const val ID_3 = "id_3"
    }
}
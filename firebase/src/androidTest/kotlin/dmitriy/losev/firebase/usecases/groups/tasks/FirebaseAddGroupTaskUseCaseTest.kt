package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseAddGroupTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddGroupTaskUseCaseTest: BaseGroupTasksUseCaseTest() {

    private val firebaseAddGroupTaskUseCase by inject<FirebaseAddGroupTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddGroupTaskUseCase.addTask(GROUP_ID, TASK_ID)

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask()

        assertEquals(TASK_ID, actualResult)
    }
}
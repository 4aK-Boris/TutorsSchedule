package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseRemoveGroupTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveGroupTaskUseCaseTest: BaseGroupTasksUseCaseTest() {

    private val firebaseRemoveGroupTaskUseCase by inject<FirebaseRemoveGroupTaskUseCase>()

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

        firebaseRemoveGroupTaskUseCase.removeTask(GROUP_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }
}
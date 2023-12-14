package dmitriy.losev.firebase.usecases.groups.tasks

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.tasks.FirebaseRemoveAllGroupTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllGroupTasksUseCaseTest: BaseGroupTasksUseCaseTest() {

    private val firebaseRemoveAllGroupTasksUseCase by inject<FirebaseRemoveAllGroupTasksUseCase>()

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

        firebaseRemoveAllGroupTasksUseCase.removeAllTasks(GROUP_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }
}
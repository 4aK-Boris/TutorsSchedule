package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseRemoveLessonTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveLessonTaskUseCaseTest: BaseLessonTasksUseCaseTest() {

    private val firebaseRemoveLessonTaskUseCase by inject<FirebaseRemoveLessonTaskUseCase>()

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

        firebaseRemoveLessonTaskUseCase.removeTask(LESSON_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }
}
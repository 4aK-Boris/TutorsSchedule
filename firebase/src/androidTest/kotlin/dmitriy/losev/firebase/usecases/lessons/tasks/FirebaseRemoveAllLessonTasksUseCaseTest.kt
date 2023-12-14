package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseRemoveAllLessonTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllLessonTasksUseCaseTest: BaseLessonTasksUseCaseTest() {

    private val firebaseRemoveAllLessonTasksUseCase by inject<FirebaseRemoveAllLessonTasksUseCase>()

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

        firebaseRemoveAllLessonTasksUseCase.removeAllTasks(LESSON_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }
}
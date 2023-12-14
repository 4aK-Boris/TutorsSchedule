package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseGetAllLessonTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllLessonTasksUseCaseTest: BaseLessonTasksUseCaseTest() {

    private val firebaseGetAllLessonTasksUseCase by inject<FirebaseGetAllLessonTasksUseCase>()

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

        val actualResult = firebaseGetAllLessonTasksUseCase.getAllTasks(LESSON_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, taskId ->
            assertEquals("${TASK_ID}-$index", taskId)
        }
    }
}
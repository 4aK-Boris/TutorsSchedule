package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseAddLessonTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddLessonTaskUseCaseTest: BaseLessonTasksUseCaseTest() {

    private val firebaseAddLessonTaskUseCase by inject<FirebaseAddLessonTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddLessonTaskUseCase.addTask(LESSON_ID, TASK_ID)

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask()

        assertEquals(TASK_ID, actualResult)
    }
}
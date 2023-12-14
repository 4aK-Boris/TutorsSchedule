package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseGetAllLessonTasksUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllLessonTasksUseCaseTest {

    private val firebaseLessonTasksRepository = mockk<FirebaseLessonTasksRepository>()

    private val firebaseGetAllLessonTasksUseCase = FirebaseGetAllLessonTasksUseCase(firebaseLessonTasksRepository)

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val tasks = listOf(TASK_ID, TASK_ID, TASK_ID)

        coEvery { firebaseLessonTasksRepository.getAllTasks(LESSON_ID) } returns tasks

        val actualResult = firebaseGetAllLessonTasksUseCase.getAllTasks(LESSON_ID)

        coVerify { firebaseLessonTasksRepository.getAllTasks(LESSON_ID) }

        assertContentEquals(tasks, actualResult)
    }

    companion object {

        private const val LESSON_ID = "lesson_id"
        private const val TASK_ID = "task_id"
    }
}
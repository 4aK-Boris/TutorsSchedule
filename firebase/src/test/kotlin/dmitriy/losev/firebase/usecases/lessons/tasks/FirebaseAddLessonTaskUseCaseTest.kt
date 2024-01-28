package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseAddLessonTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddLessonTaskUseCaseTest {

    private val firebaseLessonTasksRepository = mockk<FirebaseLessonTasksRepository>(relaxed = true)

    private val firebaseAddLessonTaskUseCase = FirebaseAddLessonTaskUseCase(firebaseLessonTasksRepository)

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddLessonTaskUseCase.addTask(LESSON_ID, TASK_ID)

        coVerify { firebaseLessonTasksRepository.addTask(LESSON_ID, TASK_ID) }
    }

    companion object {
        private const val LESSON_ID = "lesson_id"
        private const val TASK_ID = "task_id"
    }
}
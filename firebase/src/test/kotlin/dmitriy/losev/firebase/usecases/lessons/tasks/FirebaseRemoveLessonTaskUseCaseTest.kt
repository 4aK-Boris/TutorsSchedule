package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseRemoveLessonTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveLessonTaskUseCaseTest {

    private val firebaseLessonTasksRepository = mockk<FirebaseLessonTasksRepository>(relaxed = true)

    private val firebaseRemoveLessonTaskUseCase = FirebaseRemoveLessonTaskUseCase(firebaseLessonTasksRepository)

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        firebaseRemoveLessonTaskUseCase.removeTask(LESSON_ID, TASK_ID)

        coVerify { firebaseLessonTasksRepository.removeTask(LESSON_ID, TASK_ID) }
    }

    companion object {
        private const val LESSON_ID = "lesson_id"
        private const val TASK_ID = "task_id"
    }
}
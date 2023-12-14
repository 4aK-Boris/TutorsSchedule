package dmitriy.losev.firebase.usecases.lessons.tasks

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import dmitriy.losev.firebase.domain.usecases.lessons.tasks.FirebaseRemoveAllLessonTasksUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllLessonTasksUseCaseTest {

    private val firebaseLessonTasksRepository = mockk<FirebaseLessonTasksRepository>(relaxed = true)

    private val firebaseRemoveAllLessonTasksUseCase = FirebaseRemoveAllLessonTasksUseCase(firebaseLessonTasksRepository)

    @Test
    fun testRemoveAllTasks(): Unit = runBlocking {

        firebaseRemoveAllLessonTasksUseCase.removeAllTasks(LESSON_ID)

        coVerify { firebaseLessonTasksRepository.removeAllTasks(LESSON_ID) }
    }

    companion object {
        private const val LESSON_ID = "lesson_id"
    }
}
package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseRemoveGroupLessonUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveGroupLessonUseCaseTest {

    private val firebaseGroupLessonsRepository = mockk<FirebaseGroupLessonsRepository>(relaxed = true)

    private val firebaseRemoveGroupLessonUseCase = FirebaseRemoveGroupLessonUseCase(firebaseGroupLessonsRepository)

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        firebaseRemoveGroupLessonUseCase.removeLesson(GROUP_ID, LESSON_ID)

        coVerify { firebaseGroupLessonsRepository.removeLesson(GROUP_ID, LESSON_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
        private const val LESSON_ID = "lesson_id"
    }
}
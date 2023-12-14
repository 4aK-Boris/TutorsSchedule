package dmitriy.losev.firebase.usecases.lessons

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseUpdateLessonUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdateLessonUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val lesson = mockk<Lesson>()

    private val firebaseLessonsRepository = mockk<FirebaseLessonsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseUpdateLessonUseCase = FirebaseUpdateLessonUseCase(firebaseLessonsRepository, firebaseGetUseCase)

    @Test
    fun testUpdateLesson(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseUpdateLessonUseCase.updateLesson(LESSON_ID, lesson)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseLessonsRepository.updateLesson(TEACHER_ID, LESSON_ID, lesson)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val LESSON_ID = "lesson_id"
    }
}
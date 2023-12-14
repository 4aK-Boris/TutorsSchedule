package dmitriy.losev.firebase.usecases.lessons

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseAddLessonUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddLessonUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val lesson = mockk<Lesson>()

    private val firebaseLessonsRepository = mockk<FirebaseLessonsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseAddLessonUseCase = FirebaseAddLessonUseCase(firebaseLessonsRepository, firebaseGetUseCase)

    @Test
    fun testAddLesson(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseAddLessonUseCase.addLesson(lesson)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseLessonsRepository.addLesson(TEACHER_ID, any())
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}
package dmitriy.losev.firebase.usecases.lessons

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.exception.NullableLessonException
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetLessonUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val lesson = mockk<Lesson>()

    private val firebaseLessonsRepository = mockk<FirebaseLessonsRepository>()
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetLessonUseCase = FirebaseGetLessonUseCase(firebaseLessonsRepository, firebaseGetUseCase)

    @Test
    fun testGetNotNullLesson(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseLessonsRepository.getLesson(teacherId = TEACHER_ID, lessonId = LESSON_ID) } returns lesson
        every { user.uid } returns TEACHER_ID

        val actualResult = firebaseGetLessonUseCase.getLesson(LESSON_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseLessonsRepository.getLesson(teacherId = TEACHER_ID, lessonId = LESSON_ID)
        }

        assertEquals(lesson, actualResult)
    }

    @Test
    fun testGetNullableLesson(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseLessonsRepository.getLesson(teacherId = TEACHER_ID, lessonId = LESSON_ID) } returns null
        every { user.uid } returns TEACHER_ID

        assertFailsWith(exceptionClass = NullableLessonException::class) {
            firebaseGetLessonUseCase.getLesson(LESSON_ID)
        }

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseLessonsRepository.getLesson(teacherId = TEACHER_ID, lessonId = LESSON_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val LESSON_ID = "lesson_id"
    }
}
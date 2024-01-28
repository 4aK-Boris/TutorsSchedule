package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetLimitStudentLessonsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetLimitStudentLessonsUseCaseTest {

    private val firebaseStudentLessonsRepository = mockk<FirebaseStudentLessonsRepository>()

    private val firebaseGetLimitStudentLessonsUseCase = FirebaseGetLimitStudentLessonsUseCase(firebaseStudentLessonsRepository)

    @Test
    fun testGetLimitLessons(): Unit = runBlocking {

        val count = 3

        val lessons = listOf(LESSON_ID, LESSON_ID, LESSON_ID)

        coEvery { firebaseStudentLessonsRepository.getLimitLessons(STUDENT_ID, count) } returns lessons

        val actualResult = firebaseGetLimitStudentLessonsUseCase.getLimitLessons(STUDENT_ID, count)

        coVerify { firebaseStudentLessonsRepository.getLimitLessons(STUDENT_ID, count) }

        assertContentEquals(lessons, actualResult)
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val LESSON_ID = "lesson_id"
    }
}
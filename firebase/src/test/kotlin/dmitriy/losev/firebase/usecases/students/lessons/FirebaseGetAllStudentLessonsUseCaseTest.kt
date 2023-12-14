package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetAllStudentLessonsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllStudentLessonsUseCaseTest {

    private val firebaseStudentLessonsRepository = mockk<FirebaseStudentLessonsRepository>()

    private val firebaseGetAllStudentLessonsUseCase = FirebaseGetAllStudentLessonsUseCase(firebaseStudentLessonsRepository)

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val lessons = listOf(LESSON_ID, LESSON_ID, LESSON_ID)

        coEvery { firebaseStudentLessonsRepository.getAllLessons(STUDENT_ID) } returns lessons

        val actualResult = firebaseGetAllStudentLessonsUseCase.getAllLessons(STUDENT_ID)

        coVerify { firebaseStudentLessonsRepository.getAllLessons(STUDENT_ID) }

        assertContentEquals(lessons, actualResult)
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val LESSON_ID = "lesson_id"
    }
}
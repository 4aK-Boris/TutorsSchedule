package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseRemoveAllStudentLessonsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllStudentLessonsUseCaseTest {

    private val firebaseStudentLessonsRepository = mockk<FirebaseStudentLessonsRepository>(relaxed = true)

    private val firebaseRemoveAllStudentLessonsUseCase = FirebaseRemoveAllStudentLessonsUseCase(firebaseStudentLessonsRepository)

    @Test
    fun testRemoveAllLessons(): Unit = runBlocking {

        firebaseRemoveAllStudentLessonsUseCase.removeAllLessons(STUDENT_ID)

        coVerify { firebaseStudentLessonsRepository.removeAllLessons(STUDENT_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
    }
}
package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseRemoveStudentLessonUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveStudentLessonUseCaseTest {

    private val firebaseStudentLessonsRepository = mockk<FirebaseStudentLessonsRepository>(relaxed = true)

    private val firebaseRemoveStudentLessonUseCase = FirebaseRemoveStudentLessonUseCase(firebaseStudentLessonsRepository)

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        firebaseRemoveStudentLessonUseCase.removeLesson(STUDENT_ID, LESSON_ID)

        coVerify { firebaseStudentLessonsRepository.removeLesson(STUDENT_ID, LESSON_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
        private const val LESSON_ID = "lesson_id"
    }
}
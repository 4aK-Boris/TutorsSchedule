package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseAddStudentLessonUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddStudentLessonUseCaseTest {

    private val firebaseStudentLessonsRepository = mockk<FirebaseStudentLessonsRepository>(relaxed = true)

    private val firebaseAddStudentLessonUseCase = FirebaseAddStudentLessonUseCase(firebaseStudentLessonsRepository)

    @Test
    fun testAddLesson(): Unit = runBlocking {

        firebaseAddStudentLessonUseCase.addLesson(STUDENT_ID, LESSON_ID)

        coVerify { firebaseStudentLessonsRepository.addLesson(STUDENT_ID, LESSON_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
        private const val LESSON_ID = "lesson_id"
    }
}
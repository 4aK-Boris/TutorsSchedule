package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.core.usecases.students.BaseStudentLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseAddStudentLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddStudentLessonUseCaseTest: BaseStudentLessonsUseCaseTest() {

    private val firebaseAddStudentLessonUseCase by inject<FirebaseAddStudentLessonUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        firebaseAddStudentLessonUseCase.addLesson(STUDENT_ID, LESSON_ID)

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val actualResult = getLesson()

        assertEquals(LESSON_ID, actualResult)
    }
}
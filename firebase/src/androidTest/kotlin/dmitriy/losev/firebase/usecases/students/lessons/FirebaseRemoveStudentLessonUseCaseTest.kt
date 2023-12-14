package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.core.usecases.students.BaseStudentLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseRemoveStudentLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveStudentLessonUseCaseTest: BaseStudentLessonsUseCaseTest() {

    private val firebaseRemoveStudentLessonUseCase by inject<FirebaseRemoveStudentLessonUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        addLesson()

        var hasLesson = hasLesson()

        assertTrue(hasLesson)

        firebaseRemoveStudentLessonUseCase.removeLesson(STUDENT_ID, LESSON_ID)

        hasLesson = hasLesson()

        assertFalse(hasLesson)
    }
}
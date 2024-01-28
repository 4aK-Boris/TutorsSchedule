package dmitriy.losev.firebase.usecases.lessons

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonsUseCaseTest
import dmitriy.losev.core.models.types.LessonType
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseUpdateLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseUpdateLessonUseCaseTest: BaseLessonsUseCaseTest() {

    private val firebaseUpdateLessonUseCase by inject<FirebaseUpdateLessonUseCase>()

    override suspend fun setUp() {
        logIn()
        addLesson()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testUpdateLesson(): Unit = runBlocking {

        val hasLesson =  hasLesson()

        assertTrue(hasLesson)

        val newLesson = lesson.copy(price = PRICE, duration = DURATION, type = LESSON_TYPE)

        firebaseUpdateLessonUseCase.updateLesson(LESSON_ID, newLesson)

        val actualResult = getLesson()

        assertEquals(newLesson, actualResult)
    }

    companion object {
        private const val PRICE = 2000
        private const val DURATION = 60

        private val LESSON_TYPE = LessonType.STUDENT
    }
}
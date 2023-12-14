package dmitriy.losev.firebase.usecases.lessons

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseGetLessonUseCaseTest: BaseLessonsUseCaseTest() {

    private val firebaseGetLessonUseCase by inject<FirebaseGetLessonUseCase>()

    override suspend fun setUp() {
        logIn()
        addLesson()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testGetLesson(): Unit = runBlocking {

        val hasLesson =  hasLesson()

        assertTrue(hasLesson)

        val actualResult = firebaseGetLessonUseCase.getLesson(LESSON_ID)

        assertEquals(lesson, actualResult)
    }
}
package dmitriy.losev.firebase.usecases.lessons

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseAddLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddLessonUseCaseTest: BaseLessonsUseCaseTest() {

    private val firebaseAddLessonUseCase by inject<FirebaseAddLessonUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        val key = firebaseAddLessonUseCase.addLesson(lesson)

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val actualResult = getLesson(key)

        assertEquals(lesson.copy(id = key), actualResult)
    }
}
package dmitriy.losev.firebase.usecases.lessons

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseDeleteLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseDeleteLessonUseCaseTest: BaseLessonsUseCaseTest() {

    private val firebaseDeleteLessonUseCase by inject<FirebaseDeleteLessonUseCase>()

    override suspend fun setUp() {
        logIn()
        addLesson()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteLesson(): Unit = runBlocking {

        var hasLesson =  hasLesson()

        assertTrue(hasLesson)

        firebaseDeleteLessonUseCase.deleteLesson(LESSON_ID)

        hasLesson = hasLesson()

        assertFalse(hasLesson)
    }
}
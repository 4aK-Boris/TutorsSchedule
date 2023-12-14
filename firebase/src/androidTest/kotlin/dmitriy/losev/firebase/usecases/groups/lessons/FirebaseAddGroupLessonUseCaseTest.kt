package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseAddGroupLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddGroupLessonUseCaseTest: BaseGroupLessonsUseCaseTest() {

    private val firebaseAddGroupLessonUseCase by inject<FirebaseAddGroupLessonUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        firebaseAddGroupLessonUseCase.addLesson(GROUP_ID, LESSON_ID)

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val actualResult = getLesson()

        assertEquals(LESSON_ID, actualResult)
    }
}
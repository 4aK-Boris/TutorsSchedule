package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseRemoveGroupLessonUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveGroupLessonUseCaseTest: BaseGroupLessonsUseCaseTest() {

    private val firebaseRemoveGroupLessonUseCase by inject<FirebaseRemoveGroupLessonUseCase>()

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

        firebaseRemoveGroupLessonUseCase.removeLesson(GROUP_ID, LESSON_ID)

        hasLesson = hasLesson()

        assertFalse(hasLesson)
    }
}
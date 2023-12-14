package dmitriy.losev.firebase.usecases.lessons

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetLessonsUseCaseTest : BaseLessonsUseCaseTest() {

    private val firebaseGetSimpleLessonsUseCase by inject<FirebaseGetLessonsUseCase>()

    override suspend fun setUp() {
        logIn()
        addLesson(ID_1)
        addLesson(ID_2)
        addLesson(ID_3)
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testGetLessons(): Unit = runBlocking {

        val actualResult = firebaseGetSimpleLessonsUseCase.getLessons()

        assertEquals(SIZE, actualResult.size)

        actualResult.forEachIndexed { index, otherLesson ->
            assertEquals(lesson.copy(id = "id_${index + 1}"), otherLesson)
        }
    }

    companion object {
        private const val SIZE = 3

        private const val ID_1 = "id_1"
        private const val ID_2 = "id_2"
        private const val ID_3 = "id_3"
    }
}
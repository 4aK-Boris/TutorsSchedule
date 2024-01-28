package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseGetAllGroupLessonsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllGroupLessonsUseCaseTest: BaseGroupLessonsUseCaseTest() {

    private val firebaseGetAllGroupLessonsUseCase by inject<FirebaseGetAllGroupLessonsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count = count)

        val actualResult = firebaseGetAllGroupLessonsUseCase.getAllLessons(GROUP_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, lessonId ->
            assertEquals("${LESSON_ID}-$index", lessonId)
        }
    }
}
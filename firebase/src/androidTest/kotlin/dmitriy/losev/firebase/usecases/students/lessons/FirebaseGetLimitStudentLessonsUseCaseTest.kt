package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.core.usecases.students.BaseStudentLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetLimitStudentLessonsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetLimitStudentLessonsUseCaseTest: BaseStudentLessonsUseCaseTest() {

    private val firebaseGetLimitStudentLessonsUseCase by inject<FirebaseGetLimitStudentLessonsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun testGetLimitLessonsWithEnoughLessons(): Unit = runBlocking {

        val count = 10
        val lessonsCount = 20

        addLessons(count = lessonsCount)

        val actualResult = firebaseGetLimitStudentLessonsUseCase.getLimitLessons(STUDENT_ID, count)

        assertEquals(count, actualResult.size)
    }

    @Test
    fun testGetLimitLessonsWithNotEnoughLessons(): Unit = runBlocking {

        val count = 10
        val lessonsCount = 5

        addLessons(count = lessonsCount)

        val actualResult = firebaseGetLimitStudentLessonsUseCase.getLimitLessons(STUDENT_ID, count)

        assertEquals(lessonsCount, actualResult.size)
    }
}

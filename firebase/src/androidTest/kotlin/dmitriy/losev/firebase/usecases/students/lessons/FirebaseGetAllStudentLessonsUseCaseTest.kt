package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.core.usecases.students.BaseStudentLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetAllStudentLessonsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllStudentLessonsUseCaseTest: BaseStudentLessonsUseCaseTest() {

    private val firebaseGetAllStudentLessonsUseCase by inject<FirebaseGetAllStudentLessonsUseCase>()

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

        val actualResult = firebaseGetAllStudentLessonsUseCase.getAllLessons(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, lessonId ->
            assertEquals("${LESSON_ID}-$index", lessonId)
        }
    }
}
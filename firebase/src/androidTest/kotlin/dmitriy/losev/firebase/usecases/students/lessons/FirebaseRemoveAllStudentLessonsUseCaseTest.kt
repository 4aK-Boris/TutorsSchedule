package dmitriy.losev.firebase.usecases.students.lessons

import dmitriy.losev.firebase.core.usecases.students.BaseStudentLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseRemoveAllStudentLessonsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllStudentLessonsUseCaseTest: BaseStudentLessonsUseCaseTest() {

    private val firebaseRemoveAllStudentLessonsUseCase by inject<FirebaseRemoveAllStudentLessonsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count)

        var hasLessons = hasLessons()

        assertTrue(hasLessons)

        firebaseRemoveAllStudentLessonsUseCase.removeAllLessons(STUDENT_ID)

        hasLessons = hasLessons()

        assertFalse(hasLessons)
    }
}
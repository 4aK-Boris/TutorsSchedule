package dmitriy.losev.firebase.usecases.students

import dmitriy.losev.firebase.core.usecases.students.BaseStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseGetStudentUseCaseTest: BaseStudentsUseCaseTest() {

    private val firebaseGetStudentUseCase by inject<FirebaseGetStudentUseCase>()

    override suspend fun setUp() {
        logIn()
        addStudent()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testGetStudent(): Unit = runBlocking {

        val hasStudent =  hasStudent()

        assertTrue(hasStudent)

        val actualResult = firebaseGetStudentUseCase.getStudent(STUDENT_ID)

        assertEquals(student, actualResult)
    }
}
package dmitriy.losev.firebase.usecases.students

import dmitriy.losev.firebase.core.usecases.students.BaseStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.FirebaseDeleteStudentUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseDeleteStudentUseCaseTest: BaseStudentsUseCaseTest() {

    private val firebaseDeleteStudentUseCase by inject<FirebaseDeleteStudentUseCase>()

    override suspend fun setUp() {
        logIn()
        addStudent()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteStudent(): Unit = runBlocking {

        var hasStudent =  hasStudent()

        assertTrue(hasStudent)

        firebaseDeleteStudentUseCase.deleteStudent(STUDENT_ID)

        hasStudent = hasStudent()

        assertFalse(hasStudent)
    }
}
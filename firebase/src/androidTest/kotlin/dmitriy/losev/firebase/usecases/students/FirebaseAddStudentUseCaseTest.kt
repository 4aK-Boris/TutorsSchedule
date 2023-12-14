package dmitriy.losev.firebase.usecases.students

import dmitriy.losev.firebase.core.usecases.students.BaseStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.FirebaseAddStudentUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddStudentUseCaseTest: BaseStudentsUseCaseTest() {

    private val firebaseAddStudentUseCase by inject<FirebaseAddStudentUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testAddStudent(): Unit = runBlocking {

        val key = firebaseAddStudentUseCase.addStudent(student)

        val hasStudent = hasStudent()

        assertTrue(hasStudent)

        val actualResult = getStudent(key)

        assertEquals(student.copy(id = key), actualResult)
    }
}
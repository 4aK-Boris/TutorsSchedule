package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseAddGroupStudentUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddGroupStudentUseCaseTest: BaseGroupStudentsUseCaseTest() {

    private val firebaseAddGroupStudentUseCase by inject<FirebaseAddGroupStudentUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testAddStudent(): Unit = runBlocking {

        firebaseAddGroupStudentUseCase.addStudent(GROUP_ID, STUDENT_ID)

        val hasStudent = hasStudent()

        assertTrue(hasStudent)

        val actualResult = getStudent()

        assertEquals(STUDENT_ID, actualResult)
    }
}
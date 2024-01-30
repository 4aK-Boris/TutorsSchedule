package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentIdsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllGroupStudentsUseCaseTest: BaseGroupStudentsUseCaseTest() {

    private val firebaseGetGroupStudentIdsUseCase by inject<FirebaseGetGroupStudentIdsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testGetAllStudents(): Unit = runBlocking {

        val count = 10

        addStudents(count = count)

        val actualResult = firebaseGetGroupStudentIdsUseCase.getGroupStudentIds(GROUP_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, studentId ->
            assertEquals("${STUDENT_ID}-$index", studentId)
        }
    }
}
package dmitriy.losev.firebase.usecases.students

import dmitriy.losev.firebase.core.usecases.students.BaseStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetSimpleStudentsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetSimpleStudentsUseCaseTest : BaseStudentsUseCaseTest() {

    private val firebaseGetSimpleStudentsUseCase by inject<FirebaseGetSimpleStudentsUseCase>()

    override suspend fun setUp() {
        logIn()
        addStudent(ID_1)
        addStudent(ID_2)
        addStudent(ID_3)
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testGetSimpleStudents(): Unit = runBlocking {

        val actualResult = firebaseGetSimpleStudentsUseCase.getSimpleStudents()

        assertEquals(SIZE, actualResult.size)

        actualResult.forEachIndexed { index, simpleStudent ->
            assertEquals("id_${index + 1}", simpleStudent.id)
            assertEquals(NAME, simpleStudent.name)
            assertEquals(STUDENT_TYPE, simpleStudent.studentType)
        }
    }

    companion object {
        private const val SIZE = 3

        private const val ID_1 = "id_1"
        private const val ID_2 = "id_2"
        private const val ID_3 = "id_3"
    }
}
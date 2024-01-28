package dmitriy.losev.firebase.usecases.students

import dmitriy.losev.firebase.core.usecases.students.BaseStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.FirebaseUpdateStudentUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseUpdateStudentUseCaseTest: BaseStudentsUseCaseTest() {

    private val firebaseUpdateStudentUseCase by inject<FirebaseUpdateStudentUseCase>()

    override suspend fun setUp() {
        logIn()
        addStudent()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testUpdateStudent(): Unit = runBlocking {

        val hasStudent =  hasStudent()

        assertTrue(hasStudent)

        val newStudent = student.copy(
            firstName = FIRST_NAME,
            lastName = LAST_NAME,
            patronymic = NICK_NAME,
            name = NAME,
            phoneNumber = PHONE_NUMBER,
            address = ADDRESS,
            comment = COMMENT
        )

        firebaseUpdateStudentUseCase.updateStudent(STUDENT_ID, newStudent)

        val actualResult = getStudent()

        assertEquals(newStudent, actualResult)
    }

    companion object {
        private const val FIRST_NAME = "Ирина"
        private const val LAST_NAME = "Кирица"
        private const val NICK_NAME = "Курица"
        private const val NAME = "$FIRST_NAME $LAST_NAME ($NICK_NAME)"
        private const val PHONE_NUMBER = "+79684022164"
        private const val ADDRESS = "Москва, Дорожная улица, д. 16к3, кв. 20, 117545"
        private const val COMMENT = "Ира"
    }
}
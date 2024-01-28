package dmitriy.losev.firebase.repositories.students

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseStudentsRepositoryTest: BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val studentMapper by inject<StudentMapper>()

    private val studentsReference by lazy { reference.child(STUDENTS) }

    private val firebaseStudentsRepository by inject<FirebaseStudentsRepository>()

    override suspend fun tearDown() {
        deleteStudents()
    }

    @Test
    fun testAddStudent(): Unit = runBlocking {

        val key = firebaseStudentsRepository.addStudent(userUID, student)!!

        val hasStudent = hasStudent()

        assertTrue(hasStudent)

        val actualResult = getStudent(key)

        assertEquals(student.copy(id = key), actualResult)
    }

    @Test
    fun testDeleteStudent(): Unit = runBlocking {

        addStudent()

        var hasStudent =  hasStudent()

        assertTrue(hasStudent)

        firebaseStudentsRepository.deleteStudent(userUID, STUDENT_ID)

        hasStudent = hasStudent()

        assertFalse(hasStudent)
    }

    @Test
    fun testGetSimpleStudents(): Unit = runBlocking {

        val size = 3

        repeat(size) { index ->
            addStudent(id = "$STUDENT_ID$index")
        }

        val actualResult = firebaseStudentsRepository.getActiveStudents(userUID)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, simpleStudent ->
            assertEquals("$STUDENT_ID$index", simpleStudent.id)
            assertEquals(NAME, simpleStudent.name)
            assertEquals(STUDENT_TYPE, simpleStudent.isNew)
        }
    }

    @Test
    fun testGetStudent(): Unit = runBlocking {

        addStudent()

        val hasStudent = hasStudent()

        assertTrue(hasStudent)

        val actualResult = firebaseStudentsRepository.getStudent(userUID, STUDENT_ID)

        assertEquals(student, actualResult)
    }

    @Test
    fun testUpdateStudent(): Unit = runBlocking {

        addStudent()

        val hasStudent =  hasStudent()

        assertTrue(hasStudent)

        val newStudent = student.copy(
            firstName = NEW_FIRST_NAME,
            lastName = NEW_LAST_NAME,
            patronymic = NEW_NICK_NAME,
            name = NEW_NAME,
            phoneNumber = NEW_PHONE_NUMBER,
            address = NEW_ADDRESS,
            comment = NEW_COMMENT
        )

        firebaseStudentsRepository.updateStudent(userUID, STUDENT_ID, newStudent)

        val actualResult = getStudent()

        assertEquals(newStudent, actualResult)
    }

    private suspend fun addStudent() {
        studentsReference.child(userUID).child(STUDENT_ID).setValue(studentMapper.map(value = student)).await()
    }

    private suspend fun addStudent(id: String) {
        studentsReference.child(userUID).child(id).setValue(studentMapper.map(value = student.copy(id = id))).await()
    }

    private suspend fun deleteStudents() {
        studentsReference.child(userUID).get().await().children.forEach { student ->
            student.ref.removeValue().await()
        }
    }

    private suspend fun getStudent(): Student? {
        return studentsReference.child(userUID).child(STUDENT_ID).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    private suspend fun getStudent(key: String): Student? {
        return studentsReference.child(userUID).child(key).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    private suspend fun hasStudent(): Boolean {
        return studentsReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val STUDENT_ID = "702yn476f32478n632584c320496732c42"

        private const val EMAIl = "tutorsscheduletest@gmail.com"
        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
        private const val NICK_NAME = "Морковка"
        private const val PHONE_NUMBER = "+79031466339"
        private const val SKYPE = "2dima1028"
        private const val DISCORD = "4aK_Boris"
        private const val ADDRESS = "Московская область, Лыткарино, 5-й микрорайон, 2-й квартал, д. 9, кв. 75, 140082"
        private const val COMMENT = "Настя"

        private const val NAME = "$FIRST_NAME $LAST_NAME ($NICK_NAME)"

        private const val NEW_FIRST_NAME = "Ирина"
        private const val NEW_LAST_NAME = "Кирица"
        private const val NEW_NICK_NAME = "Курица"
        private const val NEW_NAME = "$NEW_FIRST_NAME $NEW_LAST_NAME ($NEW_NICK_NAME)"
        private const val NEW_PHONE_NUMBER = "+79684022164"
        private const val NEW_ADDRESS = "Москва, Дорожная улица, д. 16к3, кв. 20, 117545"
        private const val NEW_COMMENT = "Ира"

        private val STUDENT_TYPE = StudentType.NEW

        private val student = Student(
            id = STUDENT_ID,
            firstName = FIRST_NAME,
            lastName = LAST_NAME,
            patronymic = NICK_NAME,
            name = NAME,
            phoneNumber = PHONE_NUMBER,
            email = EMAIl,
            skype = SKYPE,
            discord = DISCORD,
            address = ADDRESS,
            comment = COMMENT,
            studentType = STUDENT_TYPE
        )
    }
}
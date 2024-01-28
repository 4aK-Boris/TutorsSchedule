package dmitriy.losev.firebase.core.usecases.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseStudentsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val studentMapper by inject<StudentMapper>()

    private val studentsReference by lazy { reference.child(STUDENTS) }
    private val userUID by lazy { auth.currentUser?.uid ?: throw UserNotAuthorizationException() }

    protected suspend fun addStudent() {
        studentsReference.child(userUID).child(STUDENT_ID).setValue(studentMapper.map(value = student)).await()
    }

    protected suspend fun addStudent(id: String) {
        studentsReference.child(userUID).child(id).setValue(studentMapper.map(value = student.copy(id = id))).await()
    }

    protected suspend fun deleteStudents() {
        studentsReference.child(userUID).get().await().children.forEach { student ->
            student.ref.removeValue().await()
        }
    }

    protected suspend fun getStudent(): Student? {
        return studentsReference.child(userUID).child(STUDENT_ID).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    protected suspend fun getStudent(key: String): Student? {
        return studentsReference.child(userUID).child(key).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    protected suspend fun hasStudent(): Boolean {
        return studentsReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val EMAIl = "tutorsscheduletest@gmail.com"
        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
        private const val NICK_NAME = "Морковка"
        const val NAME = "$FIRST_NAME $LAST_NAME ($NICK_NAME)"
        private const val PHONE_NUMBER = "+79031466339"
        private const val SKYPE = "2dima1028"
        private const val DISCORD = "4aK_Boris"
        private const val ADDRESS = "Московская область, Лыткарино, 5-й микрорайон, 2-й квартал, д. 9, кв. 75, 140082"
        private const val COMMENT = "Настя"

        const val STUDENT_ID = "702yn476f32478n632584c320496732c42"

        val STUDENT_TYPE = StudentType.NEW

        val student = Student(
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
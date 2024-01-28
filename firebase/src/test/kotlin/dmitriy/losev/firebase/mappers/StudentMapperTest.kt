package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.core.EMPTY_STRING
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.NameMapper
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class StudentMapperTest {

    private val nameMapper = mockk<NameMapper>()

    private val studentMapper = StudentMapper(nameMapper)

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        student: Student,
        studentDTO: StudentDTO
    ) {
        val actualResult = studentMapper.map(value = student)

        assertEquals(studentDTO.id, actualResult.id)
        assertEquals(studentDTO.firstName, actualResult.firstName)
        assertEquals(studentDTO.lastName, actualResult.lastName)
        assertEquals(studentDTO.patronymic, actualResult.patronymic)
        assertEquals(studentDTO.phoneNumber, actualResult.phoneNumber)
        assertEquals(studentDTO.email, actualResult.email)
        assertEquals(studentDTO.skype, actualResult.skype)
        assertEquals(studentDTO.discord, actualResult.discord)
        assertEquals(studentDTO.comment, actualResult.comment)
        assertEquals(studentDTO.studentType, actualResult.studentType)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        studentDTO: StudentDTO,
        student: Student
    ) {
        every { nameMapper.getName(studentDTO.firstName, studentDTO.lastName, studentDTO.patronymic) } returns student.name

        val actualResult = studentMapper.map(value = studentDTO)

        verify { nameMapper.getName(studentDTO.firstName, studentDTO.lastName, studentDTO.patronymic) }

        assertEquals(student.id, actualResult.id)
        assertEquals(student.firstName, actualResult.firstName)
        assertEquals(student.lastName, actualResult.lastName)
        assertEquals(student.patronymic, actualResult.patronymic)
        assertEquals(student.phoneNumber, actualResult.phoneNumber)
        assertEquals(student.email, actualResult.email)
        assertEquals(student.skype, actualResult.skype)
        assertEquals(student.discord, actualResult.discord)
        assertEquals(student.comment, actualResult.comment)
        assertEquals(student.studentType, actualResult.studentType)
    }

    companion object {

        private const val ID = "3421349812m4c8-327432v"
        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
        private const val PATRONYMIC = "Юрьевна"
        private const val NAME = "$LAST_NAME $FIRST_NAME $PATRONYMIC"
        private const val PHONE_NUMBER = "+79031466339"
        private const val EMAIL = "dmitriylosevxxx@gmail.com"
        private const val SKYPE = "2dima1028"
        private const val DISCORD = "4aK_Boris"
        private const val COMMENT = "Настя"

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(
                Student(
                    id = null,
                    firstName = EMPTY_STRING,
                    lastName = EMPTY_STRING,
                    patronymic = EMPTY_STRING,
                    name = EMPTY_STRING,
                    phoneNumber = EMPTY_STRING,
                    email = EMPTY_STRING,
                    skype = EMPTY_STRING,
                    discord = EMPTY_STRING,
                    comment = EMPTY_STRING,
                    studentType = StudentType.NEW
                ),
                StudentDTO(
                    id = null,
                    firstName = null,
                    lastName = null,
                    patronymic = null,
                    phoneNumber = null,
                    email = null,
                    skype = null,
                    discord = null,
                    comment = null,
                    studentType = StudentType.NEW.name,
                )
            ),
            Arguments.of(
                Student(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    patronymic = PATRONYMIC,
                    name = NAME,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    comment = COMMENT,
                    studentType = StudentType.NEW
                ),
                StudentDTO(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    patronymic = PATRONYMIC,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    comment = COMMENT,
                    studentType = StudentType.NEW.name,
                )
            )
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(
                StudentDTO(
                    id = null,
                    firstName = null,
                    lastName = null,
                    patronymic = null,
                    phoneNumber = null,
                    email = null,
                    skype = null,
                    discord = null,
                    comment = null,
                    studentType = StudentType.NEW.name,
                ),
                Student(
                    id = null,
                    firstName = EMPTY_STRING,
                    lastName = EMPTY_STRING,
                    patronymic = EMPTY_STRING,
                    name = EMPTY_STRING,
                    phoneNumber = EMPTY_STRING,
                    email = EMPTY_STRING,
                    skype = EMPTY_STRING,
                    discord = EMPTY_STRING,
                    comment = EMPTY_STRING,
                    studentType = StudentType.NEW
                )
            ),
            Arguments.of(
                StudentDTO(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    patronymic = PATRONYMIC,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    comment = COMMENT,
                    studentType = StudentType.NEW.name,
                ),
                Student(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    patronymic = PATRONYMIC,
                    name = NAME,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    comment = COMMENT,
                    studentType = StudentType.NEW
                )
            )
        )
    }
}
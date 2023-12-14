package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.core.EMPTY_STRING
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.NameMapper
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.models.types.StudentType
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
        assertEquals(studentDTO.nickName, actualResult.nickName)
        assertEquals(studentDTO.phoneNumber, actualResult.phoneNumber)
        assertEquals(studentDTO.email, actualResult.email)
        assertEquals(studentDTO.skype, actualResult.skype)
        assertEquals(studentDTO.discord, actualResult.discord)
        assertEquals(studentDTO.address, actualResult.address)
        assertEquals(studentDTO.comment, actualResult.comment)
        assertEquals(studentDTO.studentType, actualResult.studentType)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        studentDTO: StudentDTO,
        student: Student
    ) {
        every { nameMapper.map(studentDTO.firstName, studentDTO.lastName, studentDTO.nickName) } returns student.name

        val actualResult = studentMapper.map(value = studentDTO)

        verify { nameMapper.map(studentDTO.firstName, studentDTO.lastName, studentDTO.nickName) }

        assertEquals(student.id, actualResult.id)
        assertEquals(student.firstName, actualResult.firstName)
        assertEquals(student.lastName, actualResult.lastName)
        assertEquals(student.nickName, actualResult.nickName)
        assertEquals(student.phoneNumber, actualResult.phoneNumber)
        assertEquals(student.email, actualResult.email)
        assertEquals(student.skype, actualResult.skype)
        assertEquals(student.discord, actualResult.discord)
        assertEquals(student.address, actualResult.address)
        assertEquals(student.comment, actualResult.comment)
        assertEquals(student.studentType, actualResult.studentType)
    }

    companion object {

        private const val ID = "3421349812m4c8-327432v"
        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
        private const val NICK_NAME = "Морковка"
        private const val NAME = "$FIRST_NAME $LAST_NAME ($NICK_NAME)"
        private const val PHONE_NUMBER = "+79031466339"
        private const val EMAIL = "dmitriylosevxxx@gmail.com"
        private const val SKYPE = "2dima1028"
        private const val DISCORD = "4aK_Boris"
        private const val ADDRESS = "Лыткарино, Московская область, 5-й микрорайон, 2-й квартал, д. 9, кв. 75, 140082"
        private const val COMMENT = "Настя"

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(
                Student(
                    id = null,
                    firstName = EMPTY_STRING,
                    lastName = EMPTY_STRING,
                    nickName = EMPTY_STRING,
                    name = EMPTY_STRING,
                    phoneNumber = EMPTY_STRING,
                    email = EMPTY_STRING,
                    skype = EMPTY_STRING,
                    discord = EMPTY_STRING,
                    address = EMPTY_STRING,
                    comment = EMPTY_STRING,
                    studentType = StudentType.NEW
                ),
                StudentDTO(
                    id = null,
                    firstName = null,
                    lastName = null,
                    nickName = null,
                    phoneNumber = null,
                    email = null,
                    skype = null,
                    discord = null,
                    address = null,
                    comment = null,
                    studentType = StudentType.NEW.name,
                )
            ),
            Arguments.of(
                Student(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    nickName = NICK_NAME,
                    name = NAME,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    address = ADDRESS,
                    comment = COMMENT,
                    studentType = StudentType.NEW
                ),
                StudentDTO(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    nickName = NICK_NAME,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    address = ADDRESS,
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
                    nickName = null,
                    phoneNumber = null,
                    email = null,
                    skype = null,
                    discord = null,
                    address = null,
                    comment = null,
                    studentType = StudentType.NEW.name,
                ),
                Student(
                    id = null,
                    firstName = EMPTY_STRING,
                    lastName = EMPTY_STRING,
                    nickName = EMPTY_STRING,
                    name = EMPTY_STRING,
                    phoneNumber = EMPTY_STRING,
                    email = EMPTY_STRING,
                    skype = EMPTY_STRING,
                    discord = EMPTY_STRING,
                    address = EMPTY_STRING,
                    comment = EMPTY_STRING,
                    studentType = StudentType.NEW
                )
            ),
            Arguments.of(
                StudentDTO(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    nickName = NICK_NAME,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    address = ADDRESS,
                    comment = COMMENT,
                    studentType = StudentType.NEW.name,
                ),
                Student(
                    id = ID,
                    firstName = FIRST_NAME,
                    lastName = LAST_NAME,
                    nickName = NICK_NAME,
                    name = NAME,
                    phoneNumber = PHONE_NUMBER,
                    email = EMAIL,
                    skype = SKYPE,
                    discord = DISCORD,
                    address = ADDRESS,
                    comment = COMMENT,
                    studentType = StudentType.NEW
                )
            )
        )
    }
}
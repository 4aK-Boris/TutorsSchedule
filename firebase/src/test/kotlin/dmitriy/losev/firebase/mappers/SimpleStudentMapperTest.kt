package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.SimpleStudentDTO
import dmitriy.losev.firebase.data.mappers.NameMapper
import dmitriy.losev.firebase.data.mappers.SimpleStudentMapper
import dmitriy.losev.firebase.domain.models.SimpleStudent
import dmitriy.losev.firebase.domain.models.types.StudentType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class SimpleStudentMapperTest {

    private val nameMapper = mockk<NameMapper>()

    private val simpleStudentMapper = SimpleStudentMapper(nameMapper)

    @ParameterizedTest
    @MethodSource("args")
    fun testMap(
        simpleStudentDTO: SimpleStudentDTO, simpleStudent: SimpleStudent?
    ) {
        simpleStudent?.let {
            every { nameMapper.map(simpleStudentDTO.firstName, simpleStudentDTO.lastName, simpleStudentDTO.nickName) } returns simpleStudent.name
        }

        val actualResult = simpleStudentMapper.map(value = simpleStudentDTO)

        val count = if (simpleStudent == null) 0 else 1

        verify(exactly = count) { nameMapper.map(simpleStudentDTO.firstName, simpleStudentDTO.lastName, simpleStudentDTO.nickName) }

        assertEquals(simpleStudent?.id, actualResult?.id)
        assertEquals(simpleStudent?.name, actualResult?.name)
        assertEquals(simpleStudent?.studentType, actualResult?.studentType)
    }

    companion object {

        private const val ID = "3421349812m4c8-327432v"
        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
        private const val NICK_NAME = "Морковка"

        @JvmStatic
        fun args() = listOf(
            Arguments.of(
                SimpleStudentDTO(id = null, firstName = null, lastName = null, nickName = null, studentType = StudentType.NEW.name),
                null
            ),
            Arguments.of(
                SimpleStudentDTO(id = null, firstName = FIRST_NAME, lastName = null, nickName = null, studentType = StudentType.NEW.name),
                null
            ),
            Arguments.of(
                SimpleStudentDTO(id = null, firstName = null, lastName = LAST_NAME, nickName = null, studentType = StudentType.NEW.name),
                null
            ),
            Arguments.of(
                SimpleStudentDTO(id = ID, firstName = null, lastName = LAST_NAME, nickName = null, studentType = StudentType.NEW.name),
                SimpleStudent(id = ID, name = LAST_NAME, studentType = StudentType.NEW)
            ),
            Arguments.of(
                SimpleStudentDTO(id = ID, firstName = FIRST_NAME, lastName = LAST_NAME, nickName = null, studentType = StudentType.ACTIVE.name),
                SimpleStudent(id = ID, name = "$FIRST_NAME $LAST_NAME", studentType = StudentType.ACTIVE)
            ),
            Arguments.of(
                SimpleStudentDTO(id = ID, firstName = FIRST_NAME, lastName = LAST_NAME, nickName = NICK_NAME, studentType = StudentType.ARCHIVE.name),
                SimpleStudent(id = ID, name = "$FIRST_NAME $LAST_NAME ($NICK_NAME)", studentType = StudentType.ARCHIVE)
            )
        )
    }
}
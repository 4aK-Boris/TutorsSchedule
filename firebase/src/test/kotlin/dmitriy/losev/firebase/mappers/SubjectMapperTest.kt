package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.firebase.domain.models.Subject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class SubjectMapperTest {

    private val subject = mockk<Subject>()
    private val subjectDTO = mockk<SubjectDTO>()

    private val subjectMapper = SubjectMapper()

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        id: String?,
        name: String,
        expectedId: String?,
        expectedName: String?
    ) {
        every { subject.id } returns id
        every { subject.name } returns name

        val actualResult = subjectMapper.map(value = subject)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.name)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        id: String?,
        name: String?,
        expectedId: String?,
        expectedName: String
    ) {
        every { subjectDTO.id } returns id
        every { subjectDTO.name } returns name

        val actualResult = subjectMapper.map(value = subjectDTO)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.name)
    }

    companion object {

        private const val ID = "99432i,c4234-932409932=-4c32"
        private const val NAME = "Математика ЕГЭ (профиль)"

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(null, "", null, null),
            Arguments.of(null, NAME, null, NAME),
            Arguments.of(ID, "", ID, null),
            Arguments.of(ID, NAME, ID, NAME)
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(null, null, null, ""),
            Arguments.of(null, NAME, null, NAME),
            Arguments.of(ID, null, ID, ""),
            Arguments.of(ID, NAME, ID, NAME)
        )
    }
}
package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.data.mappers.SubjectMapper
import dmitriy.losev.core.models.Subject
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
        price: Int,
        expectedId: String?,
        expectedName: String?,
        expectedPrice: Int
    ) {
        every { subject.id } returns id
        every { subject.name } returns name
        every { subject.price } returns price

        val actualResult = subjectMapper.map(value = subject)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.name)
        assertEquals(expectedPrice, actualResult.price)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        id: String?,
        name: String?,
        price: Int,
        expectedId: String?,
        expectedName: String,
        expectedPrice: Int
    ) {
        every { subjectDTO.id } returns id
        every { subjectDTO.name } returns name
        every { subject.price } returns price

        val actualResult = subjectMapper.map(value = subjectDTO)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.name)
        assertEquals(expectedPrice, actualResult.price)
    }

    companion object {

        private const val ID = "99432i,c4234-932409932=-4c32"
        private const val NAME = "Математика ЕГЭ (профиль)"
        private const val PRICE = 1000

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(null, "", PRICE, null, null, PRICE),
            Arguments.of(null, NAME, PRICE, null, NAME, PRICE),
            Arguments.of(ID, "", PRICE, ID, null, PRICE),
            Arguments.of(ID, NAME, PRICE, ID, NAME, PRICE)
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(null, PRICE, null, null, "", PRICE),
            Arguments.of(null, PRICE, NAME, null, NAME, PRICE),
            Arguments.of(ID, null, PRICE, ID, "", PRICE),
            Arguments.of(ID, NAME, PRICE, ID, NAME, PRICE)
        )
    }
}
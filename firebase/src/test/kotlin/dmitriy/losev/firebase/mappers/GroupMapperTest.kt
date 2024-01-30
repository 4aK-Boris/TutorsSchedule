package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.core.models.Group
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class GroupMapperTest {

    private val group = mockk<Group>()
    private val groupDTO = mockk<GroupDTO>()

    private val groupMapper = GroupMapper()

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        id: String?,
        name: String,
        expectedId: String?,
        expectedName: String?
    ) {
        every { group.id } returns id
        every { group.name } returns name

        val actualResult = groupMapper.map(value = group)

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
        every { groupDTO.id } returns id
        every { groupDTO.name } returns name

        val actualResult = groupMapper.map(value = groupDTO)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.name)
    }

    companion object {

        private const val ID = "99432i,c4234-932409932=-4c32"
        private const val NAME = "Группа для Насти"

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
package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.mappers.NameMapper
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class NameMapperTest {

    private val nameMapper = NameMapper()

    @ParameterizedTest
    @MethodSource("args")
    fun testMap(
        firstName: String?,
        lastName: String?,
        nickName: String?,
        expectedName: String
    ) {
        val actualName = nameMapper.map(firstName, lastName, nickName)

        assertEquals(expectedName, actualName)
    }

    companion object {

        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
        private const val NICK_NAME = "Морковка"

        @JvmStatic
        fun args() = listOf(
            Arguments.of(null, null, null, ""),
            Arguments.of(FIRST_NAME, null, null, FIRST_NAME),
            Arguments.of(null, LAST_NAME, null, LAST_NAME),
            Arguments.of(null, null, NICK_NAME, "($NICK_NAME)"),
            Arguments.of(FIRST_NAME, null, NICK_NAME, "$FIRST_NAME ($NICK_NAME)"),
            Arguments.of(null, LAST_NAME, NICK_NAME, "$LAST_NAME ($NICK_NAME)"),
            Arguments.of(FIRST_NAME, LAST_NAME, NICK_NAME, "$FIRST_NAME $LAST_NAME ($NICK_NAME)"),
        )
    }
}
package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.FirebaseTokenDTO
import dmitriy.losev.firebase.data.mappers.FirebaseTokenMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FirebaseTokenMapperTest {

    private val firebaseTokenDTO = mockk<FirebaseTokenDTO>()

    private val firebaseTokenMapper = FirebaseTokenMapper()

    @ParameterizedTest
    @MethodSource("args")
    fun testMap(
        token: String,
        expectedToken: String
    ) {
        every { firebaseTokenDTO.token } returns token

        val actualResult = firebaseTokenMapper.map(value = firebaseTokenDTO)

        assertEquals(expectedToken, actualResult.token)
    }

    companion object {

        @JvmStatic
        fun args() = listOf(Arguments.of("", ""), Arguments.of("v563425gv4352435v4325v423", "v563425gv4352435v4325v423"))
    }
}
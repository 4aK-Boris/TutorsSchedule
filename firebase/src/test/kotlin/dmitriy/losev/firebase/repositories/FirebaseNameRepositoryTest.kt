package dmitriy.losev.firebase.repositories

import dmitriy.losev.firebase.data.repositories.FirebaseNameRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FirebaseNameRepositoryTest {

    private val firebaseNameRepository = FirebaseNameRepositoryImpl()

    @ParameterizedTest
    @MethodSource("firstNameArgs")
    fun testGetFirstName(displayName: String?, firstName: String?): Unit = runBlocking {

        val actualResult = firebaseNameRepository.getFirstName(displayName)

        assertEquals(firstName, actualResult)
    }

    @ParameterizedTest
    @MethodSource("lastNameArgs")
    fun testGetLastName(displayName: String?, lastName: String?): Unit = runBlocking {

        val actualResult = firebaseNameRepository.getLastName(displayName)

        assertEquals(lastName, actualResult)
    }

    companion object {

        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"

        @JvmStatic
        fun firstNameArgs() = listOf(
            Arguments.of(null, null),
            Arguments.of(" ", null),
            Arguments.of("$FIRST_NAME ", FIRST_NAME),
            Arguments.of(" $LAST_NAME", null),
            Arguments.of("$FIRST_NAME $LAST_NAME", FIRST_NAME)
        )

        @JvmStatic
        fun lastNameArgs() = listOf(
            Arguments.of(null, null),
            Arguments.of(" ", null),
            Arguments.of("$FIRST_NAME ", null),
            Arguments.of(" $LAST_NAME", LAST_NAME),
            Arguments.of("$FIRST_NAME $LAST_NAME", LAST_NAME)
        )
    }
}
package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetLastNameUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FirebaseGetLastNameUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseUserDataRepository = mockk<FirebaseUserDataRepository>()
    private val firebaseGetDisplayNameUseCase = mockk<FirebaseGetDisplayNameUseCase>()

    private val firebaseGetLastNameUseCase = FirebaseGetLastNameUseCase(firebaseUserDataRepository, firebaseGetDisplayNameUseCase)

    @ParameterizedTest
    @MethodSource("args")
    fun testGetLastNameWithoutUser(displayName: String?, lastName: String?): Unit = runBlocking {

        coEvery { firebaseGetDisplayNameUseCase.getDisplayName() } returns displayName
        coEvery { firebaseUserDataRepository.getLastName(displayName) } returns lastName

        val actualResult = firebaseGetLastNameUseCase.getLastName()

        coVerifySequence {
            firebaseGetDisplayNameUseCase.getDisplayName()
            firebaseUserDataRepository.getLastName(displayName)
        }

        assertEquals(lastName, actualResult)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun testGetLastNameWithUser(displayName: String?, lastName: String?): Unit = runBlocking {

        coEvery { firebaseGetDisplayNameUseCase.getDisplayName(user) } returns displayName
        coEvery { firebaseUserDataRepository.getLastName(displayName) } returns lastName

        val actualResult = firebaseGetLastNameUseCase.getLastName(user)

        coVerifySequence {
            firebaseGetDisplayNameUseCase.getDisplayName(user)
            firebaseUserDataRepository.getLastName(displayName)
        }

        assertEquals(lastName, actualResult)
    }

    companion object {

        @JvmStatic
        fun args() = listOf(
            Arguments.of(null, null),
            Arguments.of("Анастасия ", null),
            Arguments.of(" Маркова", "Маркова"),
            Arguments.of("Анастасия Маркова", "Маркова")
        )
    }
}
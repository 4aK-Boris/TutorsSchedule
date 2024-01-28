package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFirstNameUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FirebaseGetFirstNameUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseUserDataRepository = mockk<FirebaseUserDataRepository>()
    private val firebaseGetDisplayNameUseCase = mockk<FirebaseGetDisplayNameUseCase>()

    private val firebaseGetFirstNameUseCase = FirebaseGetFirstNameUseCase(firebaseUserDataRepository, firebaseGetDisplayNameUseCase)

    @ParameterizedTest
    @MethodSource("args")
    fun testGetFirstNameWithoutUser(displayName: String?, firstName: String?): Unit = runBlocking {

        coEvery { firebaseGetDisplayNameUseCase.getDisplayName() } returns displayName
        coEvery { firebaseUserDataRepository.getFirstName(displayName) } returns firstName

        val actualResult = firebaseGetFirstNameUseCase.getFirstName()

        coVerifySequence {
            firebaseGetDisplayNameUseCase.getDisplayName()
            firebaseUserDataRepository.getFirstName(displayName)
        }

        assertEquals(firstName, actualResult)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun testGetFirstNameWithUser(displayName: String?, firstName: String?): Unit = runBlocking {

        coEvery { firebaseGetDisplayNameUseCase.getDisplayName(user) } returns displayName
        coEvery { firebaseUserDataRepository.getFirstName(displayName) } returns firstName

        val actualResult = firebaseGetFirstNameUseCase.getFirstName(user)

        coVerifySequence {
            firebaseGetDisplayNameUseCase.getDisplayName(user)
            firebaseUserDataRepository.getFirstName(displayName)
        }

        assertEquals(firstName, actualResult)
    }

    companion object {

        @JvmStatic
        fun args() = listOf(
            Arguments.of(null, null),
            Arguments.of("Анастасия ", "Анастасия"),
            Arguments.of(" Маркова", null),
            Arguments.of("Анастасия Маркова", "Анастасия")
        )
    }
}
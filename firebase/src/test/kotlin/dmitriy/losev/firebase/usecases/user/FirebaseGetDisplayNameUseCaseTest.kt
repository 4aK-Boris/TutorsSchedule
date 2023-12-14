package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FirebaseGetDisplayNameUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetDisplayNameUseCase = FirebaseGetDisplayNameUseCase(firebaseGetUserUseCase)

    @ParameterizedTest
    @MethodSource("args")
    fun testGetDisplayNameWithoutUser(displayName: String?): Unit = runBlocking {

        every { user.displayName } returns displayName

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        val actualResult = firebaseGetDisplayNameUseCase.getDisplayName()

        coVerify { firebaseGetUserUseCase.getUserWithException() }

        assertEquals(displayName, actualResult)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun testGetDisplayNameWithUser(displayName: String?): Unit = runBlocking {

        every { user.displayName } returns displayName

        val actualResult = firebaseGetDisplayNameUseCase.getDisplayName(user)

        assertEquals(displayName, actualResult)
    }

    companion object {

        @JvmStatic
        fun args() = listOf(Arguments.of(null), Arguments.of("Анастасия Маркова"))
    }
}
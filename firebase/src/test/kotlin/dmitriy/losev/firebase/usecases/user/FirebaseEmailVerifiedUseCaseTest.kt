package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerifiedUseCase
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

class FirebaseEmailVerifiedUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseEmailVerifiedUseCase = FirebaseEmailVerifiedUseCase(firebaseGetUserUseCase)

    @ParameterizedTest
    @MethodSource("args")
    fun testIsEmailVerifiedWithoutUser(isEmailVerified: Boolean): Unit = runBlocking {

        every { user.isEmailVerified } returns isEmailVerified

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        val actualResult = firebaseEmailVerifiedUseCase.isEmailVerified()

        coVerify { firebaseGetUserUseCase.getUserWithException() }

        assertEquals(isEmailVerified, actualResult)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun testIsEmailVerifiedWithUser(isEmailVerified: Boolean): Unit = runBlocking {

        every { user.isEmailVerified } returns isEmailVerified

        val actualResult = firebaseEmailVerifiedUseCase.isEmailVerified(user)

        assertEquals(isEmailVerified, actualResult)
    }

    companion object {
        @JvmStatic
        fun args() = listOf(Arguments.of(true), Arguments.of(false))
    }
}
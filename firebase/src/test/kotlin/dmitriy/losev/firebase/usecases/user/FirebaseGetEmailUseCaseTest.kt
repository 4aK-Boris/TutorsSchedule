package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetEmailUseCase
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

class FirebaseGetEmailUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetEmailUseCase = FirebaseGetEmailUseCase(firebaseGetUserUseCase)

    @ParameterizedTest
    @MethodSource("args")
    fun testGetEmailWithoutUser(email: String?): Unit = runBlocking {

        every { user.email } returns email

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        val actualResult = firebaseGetEmailUseCase.getEmail()

        coVerify { firebaseGetUserUseCase.getUserWithException() }

        assertEquals(email, actualResult)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun testGetEmailWithUser(email: String?): Unit = runBlocking {

        every { user.email } returns email

        val actualResult = firebaseGetEmailUseCase.getEmail(user)

        assertEquals(email, actualResult)
    }

    companion object {

        @JvmStatic
        fun args() = listOf(Arguments.of(null), Arguments.of("dmitriylosevxxx@gmail.com"))
    }
}
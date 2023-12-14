package dmitriy.losev.firebase.usecases.auth

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseAuthUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseAuthUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseAuthUseCase = FirebaseAuthUseCase(firebaseGetUserUseCase)

    @Test
    fun testIsAuthWithNotNullUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithoutException() } returns user

        val actualResult = firebaseAuthUseCase.isAuth()

        coVerify { firebaseGetUserUseCase.getUserWithoutException() }

        assertTrue(actualResult)
    }

    @Test
    fun testIsAuthWithNullableUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithoutException() } returns null

        val actualResult = firebaseAuthUseCase.isAuth()

        coVerify { firebaseGetUserUseCase.getUserWithoutException() }

        assertFalse(actualResult)
    }
}
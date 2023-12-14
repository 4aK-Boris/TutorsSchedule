package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetUserUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val auth = mockk<FirebaseAuth>()

    private val firebaseGetUserUseCase = FirebaseGetUserUseCase(auth)

    @Test
    fun testGetNotNullUserWithException(): Unit = runBlocking {

        every { auth.currentUser } returns user

        val actualResult = firebaseGetUserUseCase.getUserWithException()

        assertEquals(user, actualResult)
    }

    @Test
    fun testGetNullableUserWithException(): Unit = runBlocking {

        every { auth.currentUser } returns null

        assertFailsWith(exceptionClass = UserNotAuthorizationException::class) {
            firebaseGetUserUseCase.getUserWithException()
        }
    }

    @Test
    fun testGetNotNullUserWithoutException(): Unit = runBlocking {

        every { auth.currentUser } returns user

        val actualResult = firebaseGetUserUseCase.getUserWithoutException()

        assertEquals(user, actualResult)
    }

    @Test
    fun testGetNullableUserWithoutException(): Unit = runBlocking {

        every { auth.currentUser } returns null

        val actualResult = firebaseGetUserUseCase.getUserWithoutException()

        assertEquals(null, actualResult)
    }
}
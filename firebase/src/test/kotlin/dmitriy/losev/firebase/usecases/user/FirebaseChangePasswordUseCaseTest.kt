package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.user.FirebaseChangePasswordUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirebaseChangePasswordUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val result = mockk<Void>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val task = FirebaseTask(data = result)

    private val firebaseChangePasswordUseCase =  FirebaseChangePasswordUseCase(firebaseGetUserUseCase)

    @BeforeEach
    fun setUp() {
        every { user.updatePassword(any()) } returns task
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testChangePasswordWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        firebaseChangePasswordUseCase.changePassword(PASSWORD)

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.updatePassword(PASSWORD)
        }
    }

    @Test
    fun testChangePasswordWithUser(): Unit = runBlocking {

        firebaseChangePasswordUseCase.changePassword(user, PASSWORD)

        verify { user.updatePassword(PASSWORD) }
    }

    companion object {
        private const val PASSWORD = "password"
    }
}
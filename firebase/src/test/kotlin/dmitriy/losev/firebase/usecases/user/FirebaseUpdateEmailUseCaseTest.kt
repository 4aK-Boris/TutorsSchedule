package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateEmailUseCase
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

class FirebaseUpdateEmailUseCaseTest {

    private val user = mockk<FirebaseUser>(relaxed = true)
    private val result = mockk<Void>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val task = FirebaseTask(data = result)

    private val firebaseUpdateEmailUseCase = FirebaseUpdateEmailUseCase(firebaseGetUserUseCase)

    @BeforeEach
    fun setUp() {
        every { user.verifyBeforeUpdateEmail(any()) } returns task
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testUpdateEmailWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        firebaseUpdateEmailUseCase.updateEmail(EMAIL)

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.verifyBeforeUpdateEmail(EMAIL)
        }
    }

    @Test
    fun testUpdateEmailWithUser(): Unit = runBlocking {

        firebaseUpdateEmailUseCase.updateEmail(user, EMAIL)

        verify { user.verifyBeforeUpdateEmail(EMAIL) }
    }

    companion object {
        private const val EMAIL = "dmitriylosevxxx@gmail.com"
    }
}
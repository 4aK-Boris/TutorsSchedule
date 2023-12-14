package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerificationUseCase
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

class FirebaseEmailVerificationUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val result = mockk<Void>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val task = FirebaseTask(data = result)

    private val firebaseEmailVerificationUseCase =  FirebaseEmailVerificationUseCase(firebaseGetUserUseCase)

    @BeforeEach
    fun setUp() {
        every { user.sendEmailVerification() } returns task
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testSendVerificationMessageWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        firebaseEmailVerificationUseCase.sendVerificationMessage()

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.sendEmailVerification()
        }
    }

    @Test
    fun testSendVerificationMessageWithUser(): Unit = runBlocking {

        firebaseEmailVerificationUseCase.sendVerificationMessage(user)

        verify { user.sendEmailVerification() }
    }
}
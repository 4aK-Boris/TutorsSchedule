package dmitriy.losev.firebase.usecases.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailRegistrationUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseEmailRegistrationUseCaseTest {

    private val auth = mockk<FirebaseAuth>(relaxed = true)
    private val result = mockk<AuthResult>()

    private val task = FirebaseTask(data = result)

    private val firebaseEmailRegistrationUseCase = FirebaseEmailRegistrationUseCase(auth)

    @Test
    fun testAuthWithEmail(): Unit = runBlocking {

        every { auth.createUserWithEmailAndPassword(EMAIL, PASSWORD) } returns task

        firebaseEmailRegistrationUseCase.registrationWithEmail(EMAIL, PASSWORD)

        verify { auth.createUserWithEmailAndPassword(EMAIL, PASSWORD) }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}
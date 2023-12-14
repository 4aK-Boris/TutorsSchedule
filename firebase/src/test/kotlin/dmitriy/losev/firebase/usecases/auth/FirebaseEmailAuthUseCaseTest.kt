package dmitriy.losev.firebase.usecases.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailAuthUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseEmailAuthUseCaseTest {

    private val auth = mockk<FirebaseAuth>(relaxed = true)
    private val result = mockk<AuthResult>()

    private val task = FirebaseTask(data = result)

    private val firebaseEmailAuthUseCase = FirebaseEmailAuthUseCase(auth)

    @Test
    fun testAuthWithEmail(): Unit = runBlocking {

        every { auth.signInWithEmailAndPassword(EMAIL, PASSWORD) } returns task

        firebaseEmailAuthUseCase.authWithEmail(EMAIL, PASSWORD)

        verify { auth.signInWithEmailAndPassword(EMAIL, PASSWORD) }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}
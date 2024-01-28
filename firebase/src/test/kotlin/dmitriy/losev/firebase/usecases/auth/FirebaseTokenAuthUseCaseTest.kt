package dmitriy.losev.firebase.usecases.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseTokenAuthUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseTokenAuthUseCaseTest {

    private val firebaseToken = mockk<FirebaseToken>(relaxed = true)
    private val result = mockk<AuthResult>()

    private val auth = mockk<FirebaseAuth>()

    private val task = FirebaseTask(data = result)

    private val firebaseTokenAuthUseCase = FirebaseTokenAuthUseCase(auth)

    @Test
    fun testAuthWithToken(): Unit = runBlocking {

        every { auth.signInWithCustomToken(any<String>()) } returns task

        firebaseTokenAuthUseCase.authWithToken(firebaseToken)

        verify { auth.signInWithCustomToken(any<String>()) }
    }
}
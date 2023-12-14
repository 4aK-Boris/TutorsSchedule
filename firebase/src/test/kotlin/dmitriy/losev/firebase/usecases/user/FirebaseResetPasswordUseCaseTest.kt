package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.user.FirebaseResetPasswordUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseResetPasswordUseCaseTest {

    private val auth = mockk<FirebaseAuth>()
    private val result = mockk<Void>()

    private val task = FirebaseTask(data = result)

    private val firebaseResetPasswordUseCase = FirebaseResetPasswordUseCase(auth)

    @Test
    fun testSendPasswordResetEmail() = runBlocking {

        every { auth.setLanguageCode(LANGUAGE_CODE) } returns Unit
        every { auth.sendPasswordResetEmail(EMAIl) } returns task

        firebaseResetPasswordUseCase.sendPasswordResetEmail(EMAIl)

        verifySequence {
            auth.setLanguageCode(LANGUAGE_CODE)
            auth.sendPasswordResetEmail(EMAIl)
        }
    }

    companion object {
        private const val LANGUAGE_CODE = "ru"
        private const val EMAIl = "dmitriylosevxxx@gmail.com"
    }
}
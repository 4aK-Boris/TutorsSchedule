package dmitriy.losev.firebase.usecases.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseAuthUseCaseTest
import dmitriy.losev.firebase.core.EMAIl
import dmitriy.losev.firebase.core.PASSWORD
import dmitriy.losev.firebase.core.exception.FirebaseAuthInvalidCredentialsException
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailAuthUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseEmailAuthUseCaseTest: BaseAuthUseCaseTest() {

    private val firebaseEmailAuthUseCase by inject<FirebaseEmailAuthUseCase>()

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testAuthWithEmail(): Unit = runBlocking {

        firebaseEmailAuthUseCase.authWithEmail(EMAIl, PASSWORD)

        assertTrue(isAuth)
    }

    @Test
    fun testAuthWithEmailWithException(): Unit = runBlocking {

        assertFailsWith(exceptionClass = FirebaseAuthInvalidCredentialsException::class) {
            firebaseEmailAuthUseCase.authWithEmail(INCORRECT_EMAIL, INCORRECT_PASSWORD)
        }
    }

    companion object {
        private const val INCORRECT_EMAIL = "incorrect_email"
        private const val INCORRECT_PASSWORD = "incorrect_password"
    }
}
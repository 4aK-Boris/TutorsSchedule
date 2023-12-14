package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.domain.usecases.user.FirebaseChangePasswordUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseChangePasswordUseCaseTest : BaseUserUseCaseTest() {

    private val firebaseChangePasswordUseCase by inject<FirebaseChangePasswordUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
        logIn(NEW_PASSWORD)
        returnPassword()
        logOut()
    }

    @Test
    fun testChangePassword(): Unit = runBlocking {

        firebaseChangePasswordUseCase.changePassword(NEW_PASSWORD)

        logOut()

        assertFalse(isAuth)

        logIn(NEW_PASSWORD)

        assertTrue(isAuth)
    }

    @Test
    fun testChangeIncorrectPassword(): Unit = runBlocking {

        firebaseChangePasswordUseCase.changePassword(NEW_PASSWORD)

        logOut()

        assertFalse(isAuth)

        assertFailsWith(exceptionClass = FirebaseAuthInvalidCredentialsException::class) {
            logIn(INCORRECT_PASSWORD)
        }

        assertFalse(isAuth)
    }

    companion object {
        private const val NEW_PASSWORD = "new_password"
        private const val INCORRECT_PASSWORD = "incorrect_password"
    }
}
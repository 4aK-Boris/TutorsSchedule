package dmitriy.losev.firebase.usecases.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseAuthUseCaseTest
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailRegistrationUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseEmailRegistrationUseCaseTest: BaseAuthUseCaseTest() {

    private val firebaseEmailRegistrationUseCase by inject<FirebaseEmailRegistrationUseCase>()

    override suspend fun tearDown() {
        deleteAccount()
        logOut()
    }

    @Test
    fun testRegistrationWithEmail(): Unit = runBlocking {

        firebaseEmailRegistrationUseCase.registrationWithEmail(EMAIL, PASSWORD)

        logIn(EMAIL, PASSWORD)

        assertTrue(isAuth)
    }

    companion object {
        private const val EMAIL = "dlosev450@gmail.com"
        private const val PASSWORD = "dlosev450"
    }
}
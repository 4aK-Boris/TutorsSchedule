package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.EMAIl
import dmitriy.losev.firebase.domain.usecases.user.FirebaseResetPasswordUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseResetPasswordUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseResetPasswordUseCase by inject<FirebaseResetPasswordUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testSendPasswordResetEmail(): Unit = runBlocking {

        firebaseResetPasswordUseCase.sendPasswordResetEmail(EMAIl)

        logOut()
        logIn()

        assertTrue(isAuth)
    }
}
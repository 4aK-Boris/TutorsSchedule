package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerificationUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseEmailVerificationUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseEmailVerificationUseCase by inject<FirebaseEmailVerificationUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testSendVerificationMessage(): Unit = runBlocking {

        firebaseEmailVerificationUseCase.sendVerificationMessage()

        assertTrue(isEmailVerified)
    }
}
package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerifiedUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseEmailVerifiedUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseEmailVerifiedUseCase by inject<FirebaseEmailVerifiedUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testIsEmailVerified(): Unit = runBlocking {

        val isEmailVerified = firebaseEmailVerifiedUseCase.isEmailVerified()

        assertTrue(isEmailVerified)
    }
}
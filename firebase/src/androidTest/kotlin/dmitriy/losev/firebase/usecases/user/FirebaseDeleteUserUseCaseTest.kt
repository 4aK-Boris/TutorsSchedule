package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.domain.usecases.user.FirebaseDeleteUserUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertFails

@RunWith(AndroidJUnit4::class)
class FirebaseDeleteUserUseCaseTest : BaseUserUseCaseTest() {

    private val firebaseDeleteUserUseCase by inject<FirebaseDeleteUserUseCase>()

    override suspend fun setUp() {
        createAccount(EMAIl, PASSWORD)
        logIn(EMAIl, PASSWORD)
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteAccount(): Unit = runBlocking {

        firebaseDeleteUserUseCase.deleteAccount()

        assertFails {
            logIn(EMAIl, PASSWORD)
        }
    }

    companion object {
        private const val EMAIl = "dlosev450@gmail.com"
        private const val PASSWORD = "dlosev450"
    }
}
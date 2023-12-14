package dmitriy.losev.firebase.usecases.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseAuthUseCaseTest
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseAuthUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseAuthUseCaseTest: BaseAuthUseCaseTest() {

    private val firebaseAuthUseCase by inject<FirebaseAuthUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testIsAuthWithAuth(): Unit = runBlocking {

        val actualResult = firebaseAuthUseCase.isAuth()

        assertTrue(actualResult)
    }

    @Test
    fun testIsAuthWithoutAuth(): Unit = runBlocking {

        logOut()

        val actualResult = firebaseAuthUseCase.isAuth()

        assertFalse(actualResult)
    }
}
package dmitriy.losev.firebase.usecases.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseAuthUseCaseTest
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertFalse

@RunWith(AndroidJUnit4::class)
class FirebaseLogOutUseCaseTest: BaseAuthUseCaseTest() {

    private val firebaseLogOutUseCase by inject<FirebaseLogOutUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testLogOut(): Unit = runBlocking {

        firebaseLogOutUseCase.logOut()

        assertFalse(isAuth)
    }
}
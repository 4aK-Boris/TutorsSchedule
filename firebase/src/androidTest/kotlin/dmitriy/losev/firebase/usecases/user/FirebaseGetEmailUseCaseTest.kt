package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.EMAIl
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetEmailUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseGetEmailUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseGetEmailUseCase by inject<FirebaseGetEmailUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetEmail(): Unit = runBlocking {

        val actualResult = firebaseGetEmailUseCase.getEmail()

        assertEquals(EMAIl, actualResult)
    }
}
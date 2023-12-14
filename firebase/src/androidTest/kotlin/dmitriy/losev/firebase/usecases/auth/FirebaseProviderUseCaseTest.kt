package dmitriy.losev.firebase.usecases.auth

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseAuthUseCaseTest
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseProviderUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseProviderUseCaseTest: BaseAuthUseCaseTest() {

    private val firebaseProviderUseCase by inject<FirebaseProviderUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetProvider(): Unit = runBlocking {

        val actualResult = firebaseProviderUseCase.getProvider()

        assertEquals(PROVIDER, actualResult)
    }

    companion object {
        private const val PROVIDER = "password"
    }
}
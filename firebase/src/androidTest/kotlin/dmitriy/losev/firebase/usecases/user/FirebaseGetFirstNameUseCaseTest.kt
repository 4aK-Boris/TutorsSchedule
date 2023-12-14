package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.FIRST_NAME
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFirstNameUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseGetFirstNameUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseGetFirstNameUseCase by inject<FirebaseGetFirstNameUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetFirstName(): Unit = runBlocking {

        val actualResult = firebaseGetFirstNameUseCase.getFirstName()

        assertEquals(FIRST_NAME, actualResult)
    }
}
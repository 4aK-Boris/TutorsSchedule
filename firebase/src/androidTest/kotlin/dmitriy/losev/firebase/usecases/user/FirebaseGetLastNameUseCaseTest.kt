package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.LAST_NAME
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetLastNameUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseGetLastNameUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseGetLastNameUseCase by inject<FirebaseGetLastNameUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetLastName(): Unit = runBlocking {

        val actualResult = firebaseGetLastNameUseCase.getLastName()

        assertEquals(LAST_NAME, actualResult)
    }
}
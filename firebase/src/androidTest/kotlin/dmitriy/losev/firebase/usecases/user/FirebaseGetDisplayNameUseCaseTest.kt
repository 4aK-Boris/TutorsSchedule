package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.DISPLAY_NAME
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetDisplayNameUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseGetDisplayNameUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseGetDisplayNameUseCase by inject<FirebaseGetDisplayNameUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetDisplayName(): Unit = runBlocking {

        val actualResult = firebaseGetDisplayNameUseCase.getDisplayName()

        assertEquals(DISPLAY_NAME, actualResult)
    }
}
package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.AVATAR_URI
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetAvatarUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseGetAvatarUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseGetAvatarUseCase by inject<FirebaseGetAvatarUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetAvatarUri(): Unit = runBlocking {

        val actualResult = firebaseGetAvatarUseCase.getAvatarUri()

        assertEquals(AVATAR_URI, actualResult)
    }
}
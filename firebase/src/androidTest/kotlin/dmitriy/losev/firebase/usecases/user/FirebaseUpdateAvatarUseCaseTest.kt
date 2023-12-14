package dmitriy.losev.firebase.usecases.user

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.AVATAR_URI
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateAvatarUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseUpdateAvatarUseCaseTest : BaseUserUseCaseTest() {

    private val newAvatarUri = Uri.parse(NEW_AVATAR_URL)

    private val firebaseUpdateAvatarUseCase by inject<FirebaseUpdateAvatarUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        returnAvatar()
        logOut()
    }

    @Test
    @Ignore("don't work")
    fun testUpdateAvatarWithUrl(): Unit = runBlocking {

        assertEquals(AVATAR_URI, avatarUri)

        firebaseUpdateAvatarUseCase.updateAvatar(NEW_AVATAR_URL)

        assertEquals(newAvatarUri, avatarUri)
    }

    @Test
    fun testUpdateAvatarWithUri(): Unit = runBlocking {

        assertEquals(AVATAR_URI, avatarUri)

        firebaseUpdateAvatarUseCase.updateAvatar(newAvatarUri)

        assertEquals(newAvatarUri, avatarUri)
    }

    companion object {
        private const val NEW_AVATAR_URL = "https://sun9-16.userapi.com/impg/nBLBlF65P8QUppFJ620SSlwW3QzIDzgXdNM8PA/oo4baKP4rwA.jpg?size=1620x2160&quality=95&sign=be4275a920422569f07eb6921a0e5cb8&type=album"
    }
}
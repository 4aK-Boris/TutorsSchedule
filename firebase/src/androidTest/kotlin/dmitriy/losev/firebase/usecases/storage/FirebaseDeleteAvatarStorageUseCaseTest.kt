package dmitriy.losev.firebase.usecases.storage

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.storage.StorageException
import dmitriy.losev.firebase.core.usecases.BaseStorageUseCaseTest
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseDeleteAvatarStorageUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import java.io.File
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class FirebaseDeleteAvatarStorageUseCaseTest : BaseStorageUseCaseTest() {

    private val firebaseDeleteAvatarStorageUseCase by inject<FirebaseDeleteAvatarStorageUseCase>()

    private val uri = Uri.fromFile(File(AVATAR_PATH))

    override suspend fun setUp() {
        logIn()
        uploadAvatar()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteAvatar(): Unit = runBlocking {

        val avatarUri = getAvatar()

        assertNotNull(avatarUri)

        firebaseDeleteAvatarStorageUseCase.deleteAvatar(uri)

        assertFailsWith(exceptionClass = StorageException::class) {
            getAvatar()
        }
    }

}
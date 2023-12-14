package dmitriy.losev.firebase.usecases.storage

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseStorageUseCaseTest
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseUploadAvatarStorageUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import java.io.File
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseUploadAvatarStorageUseCaseTest : BaseStorageUseCaseTest() {

    private val firebaseUploadAvatarStorageUseCase by inject<FirebaseUploadAvatarStorageUseCase>()

    private val uri = Uri.fromFile(File(AVATAR_PATH))

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteAvatar()
        logOut()
    }

    @Test
    fun testUploadAvatar(): Unit = runBlocking {

        val uri = firebaseUploadAvatarStorageUseCase.uploadAvatar(uri)

        val expectedUri = getAvatar()

        assertEquals(expectedUri, uri)
    }
}
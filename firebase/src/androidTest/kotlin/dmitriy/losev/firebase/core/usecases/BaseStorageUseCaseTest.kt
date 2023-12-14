package dmitriy.losev.firebase.core.usecases

import android.net.Uri
import com.google.firebase.storage.StorageReference
import dmitriy.losev.firebase.core.AVATARS
import dmitriy.losev.firebase.core.BaseUseCaseTest
import kotlinx.coroutines.tasks.await
import org.koin.test.inject
import java.io.File

abstract class BaseStorageUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<StorageReference>()

    private val avatarUri = Uri.fromFile(File(AVATAR_PATH))

    private val avatarsReference by lazy { reference.child(AVATARS) }

    suspend fun getAvatar(): Uri? {
        return avatarsReference.child(AVATAR_NAME).downloadUrl.await()
    }

    suspend fun uploadAvatar() {
        val avatarReference = avatarsReference.child(AVATAR_NAME)
        avatarReference.putFile(avatarUri).await()
    }

    suspend fun deleteAvatar() {
        avatarsReference.child(AVATAR_NAME).delete().await()
    }

    companion object {
        const val AVATAR_PATH = "/data/data/dmitriy.losev.firebase.test/files/anastasia_markova.jpg"
        const val AVATAR_NAME = "anastasia_markova.jpg"
    }
}
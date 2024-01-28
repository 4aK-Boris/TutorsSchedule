package dmitriy.losev.firebase.usecases.storage

import android.net.Uri
import com.google.firebase.storage.StorageReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseDeleteAvatarStorageUseCase
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteAvatarStorageUseCaseTest {

    private val uri = mockk<Uri>()
    private val result = mockk<Void>()

    private val reference = mockk<StorageReference>()
    private val avatarsReference = mockk<StorageReference>()
    private val avatarReference = mockk<StorageReference>()

    private val task = FirebaseTask(data = result)

    private val firebaseDeleteAvatarStorageUseCase = FirebaseDeleteAvatarStorageUseCase(reference)

    @Test
    fun testDeleteNotNullAvatar(): Unit = runBlocking {

        every { uri.lastPathSegment } returns LAST_PATH_SEGMENT
        every { reference.child(AVATARS) } returns avatarsReference
        every { avatarsReference.child(LAST_PATH_SEGMENT) } returns avatarReference

        every { avatarReference.delete() } returns task

        firebaseDeleteAvatarStorageUseCase.deleteAvatar(uri)

        verifySequence {
            uri.lastPathSegment
            reference.child(AVATARS)
            avatarsReference.child(LAST_PATH_SEGMENT)
            avatarReference.delete()
        }
    }

    @Test
    fun testDeleteNullableAvatar(): Unit = runBlocking {

        firebaseDeleteAvatarStorageUseCase.deleteAvatar(imageUri = null)

        verify { reference.child(any()) wasNot Called }
        verify { avatarReference.delete() wasNot Called }
    }

    companion object {
        private const val LAST_PATH_SEGMENT = "last_path_segment"
        private const val AVATARS = "avatars"
    }
}
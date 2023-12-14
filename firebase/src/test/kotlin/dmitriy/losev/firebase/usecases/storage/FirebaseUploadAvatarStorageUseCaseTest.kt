package dmitriy.losev.firebase.usecases.storage

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseUploadAvatarStorageUseCase
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUploadAvatarStorageUseCaseTest {

    private val uri = mockk<Uri>()

    private val uploadTask = mockk<UploadTask> {
        every { exception } returns null
        every { isCanceled } returns false
        every { isComplete } returns true
        every { result } returns uploadTaskResult
    }
    private val uploadTaskResult = mockk<UploadTask.TaskSnapshot>()

    private val reference = mockk<StorageReference>()
    private val avatarsReference = mockk<StorageReference>()
    private val avatarReference = mockk<StorageReference>()

    private val task = FirebaseTask(data = uri)

    private val firebaseUploadAvatarStorageUseCase = FirebaseUploadAvatarStorageUseCase(reference)

    @Test
    fun testUploadNotNullAvatar(): Unit = runBlocking {

        every { uri.lastPathSegment } returns LAST_PATH_SEGMENT
        every { reference.child(AVATARS) } returns avatarsReference
        every { avatarsReference.child(LAST_PATH_SEGMENT) } returns avatarReference

        every { avatarReference.putFile(uri) } returns uploadTask

        every { avatarReference.downloadUrl } returns task

        firebaseUploadAvatarStorageUseCase.uploadAvatar(uri)

        verifySequence {
            uri.lastPathSegment
            reference.child(AVATARS)
            avatarsReference.child(LAST_PATH_SEGMENT)
            avatarReference.putFile(uri)
            avatarReference.downloadUrl
        }
    }

    @Test
    fun testUploadNullableAvatar(): Unit = runBlocking {

        firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri = null)

        verify { reference.child(any()) wasNot Called }
        verify { avatarReference.putFile(any()) wasNot Called }
        verify { avatarReference.downloadUrl wasNot Called }
    }

    companion object {
        private const val LAST_PATH_SEGMENT = "last_path_segment"
        private const val AVATARS = "avatars"
    }
}
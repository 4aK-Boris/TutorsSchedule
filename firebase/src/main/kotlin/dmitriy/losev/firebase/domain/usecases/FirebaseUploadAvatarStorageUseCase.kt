package dmitriy.losev.firebase.domain.usecases

import android.net.Uri
import com.google.firebase.storage.StorageReference
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUploadAvatarStorageUseCase(
    errorHandler: ErrorHandler,
    reference: StorageReference
) : FirebaseBaseUseCase(errorHandler) {

    private val avatarReference = reference.child("avatars")

    suspend fun uploadAvatar(imageUri: Uri?): Result<Uri> = safeCallNullable {
        imageUri?.lastPathSegment?.let { lastPathSegment ->
            val riversRef = avatarReference.child(lastPathSegment)
            riversRef.putFile(imageUri).await()
            riversRef.downloadUrl.await()
        }
    }
}
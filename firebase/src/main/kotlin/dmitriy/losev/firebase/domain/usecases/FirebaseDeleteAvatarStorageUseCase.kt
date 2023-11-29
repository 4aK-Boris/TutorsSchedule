package dmitriy.losev.firebase.domain.usecases

import android.net.Uri
import com.google.firebase.storage.StorageReference
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseDeleteAvatarStorageUseCase(
    errorHandler: ErrorHandler,
    reference: StorageReference
) : FirebaseBaseUseCase(errorHandler) {

    private val avatarReference = reference.child("avatars")

    suspend fun deleteAvatar(imageUri: Uri?): Result<Unit> = safeCall {
        imageUri?.lastPathSegment?.let { lastPathSegment ->
            val riversRef = avatarReference.child(lastPathSegment)
            riversRef.delete()
        }
    }
}
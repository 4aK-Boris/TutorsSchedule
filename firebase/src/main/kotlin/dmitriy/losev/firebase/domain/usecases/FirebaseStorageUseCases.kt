package dmitriy.losev.firebase.domain.usecases

import android.app.Application
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.UploadAvatarException
import kotlinx.coroutines.tasks.await

class FirebaseStorageUseCases(
    errorHandler: ErrorHandler,
    private val application: Application,
    private val storage: FirebaseStorage,
    reference: StorageReference
) : FirebaseBaseUseCase(errorHandler = errorHandler) {

    private val avatarReference = reference.child("avatars")

    suspend fun uploadAvatar(imageUri: Uri?) = safeCall {
        imageUri?.let {
            val riversRef = avatarReference.child("${imageUri.lastPathSegment}")
            riversRef.putFile(imageUri).await()
            riversRef.downloadUrl.await()
        } ?: throw UploadAvatarException()
    }
}
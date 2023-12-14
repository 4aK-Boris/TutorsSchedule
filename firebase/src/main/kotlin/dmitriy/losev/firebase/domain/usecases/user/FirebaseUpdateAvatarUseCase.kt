package dmitriy.losev.firebase.domain.usecases.user

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUriUseCase
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseUploadAvatarStorageUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUpdateAvatarUseCase(
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase,
    private val firebaseUriUseCase: FirebaseUriUseCase,
    private val firebaseUploadAvatarStorageUseCase: FirebaseUploadAvatarStorageUseCase
) : FirebaseBaseUseCase() {

    suspend fun updateAvatar(imageUrl: String) {
        updateAvatar(user = firebaseGetUserUseCase.getUserWithException(), imageUrl = imageUrl)
    }

    suspend fun updateAvatar(user: FirebaseUser, imageUrl: String) {
        val uri = firebaseUriUseCase.convertUrlToUri(imageUrl)
        val imageUri = firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri = uri)
        updateAvatar(user, imageUri)
    }

    suspend fun updateAvatar(imageUri: Uri?) {
        updateAvatar(user = firebaseGetUserUseCase.getUserWithException(), imageUri = imageUri)
    }

    suspend fun updateAvatar(user: FirebaseUser, imageUri: Uri?): Unit = convertException {
        val profileUpdates = userProfileChangeRequest {
            photoUri = imageUri
        }
        user.updateProfile(profileUpdates).await()
    }
}
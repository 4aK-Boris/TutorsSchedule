package dmitriy.losev.firebase.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUpdateAvatarUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUploadAvatarStorageUseCase: FirebaseUploadAvatarStorageUseCase,
) : FirebaseBaseUseCase(errorHandler) {

    suspend fun updateAvatarWithImageUri(user: FirebaseUser, imageUri: Uri?): Result<Unit> = safeCall {
            firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri = imageUri)
                .processingNullableResult { avatarUri ->
                    updateAvatarWithImageUrl(user, imageUri = avatarUri)
                }
        }

    suspend fun updateAvatarWithImageUrl(
        user: FirebaseUser,
        imageUri: Uri?
    ): Result<Unit> = safeCall {
        val profileUpdates = userProfileChangeRequest {
            photoUri = imageUri
        }
        user.updateProfile(profileUpdates).await()
    }
}
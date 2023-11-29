package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseDeleteAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateAvatarUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateAvatarUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUpdateAvatarUseCase: FirebaseUpdateAvatarUseCase,
    private val firebaseDeleteAvatarStorageUseCase: FirebaseDeleteAvatarStorageUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun updateAvatar(
        user: FirebaseUser,
        avatarUri: Uri?,
        oldAvatarUri: Uri?
    ): Result<Unit> = safeReturnCall {
        firebaseDeleteAvatarStorageUseCase.deleteAvatar(imageUri = oldAvatarUri).processingResult {
            firebaseUpdateAvatarUseCase.updateAvatarWithImageUri(user, avatarUri)
        }
    }
}
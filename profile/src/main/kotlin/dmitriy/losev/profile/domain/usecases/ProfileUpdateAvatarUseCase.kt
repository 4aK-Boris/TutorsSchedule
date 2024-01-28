package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseDeleteAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseUploadAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateAvatarUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateAvatarUseCase(
    private val firebaseUpdateAvatarUseCase: FirebaseUpdateAvatarUseCase,
    private val firebaseUploadAvatarStorageUseCase: FirebaseUploadAvatarStorageUseCase,
    private val firebaseDeleteAvatarStorageUseCase: FirebaseDeleteAvatarStorageUseCase
) : ProfileBaseUseCase() {

    suspend fun updateAvatar(avatarUri: Uri?, oldAvatarUri: Uri?) {
        if (oldAvatarUri != avatarUri && avatarUri != null) {
            firebaseDeleteAvatarStorageUseCase.deleteAvatar(imageUri = oldAvatarUri)
            val newAvatarUri = firebaseUploadAvatarStorageUseCase.uploadAvatar(avatarUri)
            firebaseUpdateAvatarUseCase.updateAvatar(newAvatarUri)
        }
    }
}
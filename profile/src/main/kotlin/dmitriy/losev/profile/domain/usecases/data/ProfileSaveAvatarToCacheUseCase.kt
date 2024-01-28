package dmitriy.losev.profile.domain.usecases.data

import android.net.Uri
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.AvatarRepository

class ProfileSaveAvatarToCacheUseCase(
    private val avatarRepository: AvatarRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : ProfileBaseUseCase() {

    suspend fun saveAvatar(avatarUri: Uri?) {
        firebaseGetUserUseCase.getUserWithoutException()?.let { user ->
            val bytes = avatarUri?.let { avatarRepository.downloadAvatar(avatarUri.toString()) }
            return avatarRepository.saveAvatarToCache(userId = user.uid, avatar = bytes)
        }
    }
}
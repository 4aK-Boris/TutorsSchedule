package dmitriy.losev.profile.domain.usecases.data

import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.AvatarRepository

class ProfileGetAvatarFromCacheUseCase(
    private val avatarRepository: AvatarRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : ProfileBaseUseCase() {

    suspend fun getAvatar(): ByteArray? {
        return firebaseGetUserUseCase.getUserWithoutException()?.let { user ->
            avatarRepository.loadAvatarFromCache(userId = user.uid)
        }
    }
}
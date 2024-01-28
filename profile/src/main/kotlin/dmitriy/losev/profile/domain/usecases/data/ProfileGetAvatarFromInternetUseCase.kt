package dmitriy.losev.profile.domain.usecases.data

import android.net.Uri
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.AvatarRepository

class ProfileGetAvatarFromInternetUseCase(private val avatarRepository: AvatarRepository) : ProfileBaseUseCase() {

    suspend fun getAvatar(uri: Uri?): ByteArray? {
        return uri?.let { avatarRepository.downloadAvatar(uri.toString()) }
    }
}
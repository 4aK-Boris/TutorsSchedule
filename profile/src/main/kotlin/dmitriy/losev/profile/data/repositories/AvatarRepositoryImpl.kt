package dmitriy.losev.profile.data.repositories

import dmitriy.losev.core.cache.CacheFileHandler
import dmitriy.losev.profile.data.network.ProfileNetwork
import dmitriy.losev.profile.domain.repositories.AvatarRepository

class AvatarRepositoryImpl(
    private val cacheFileHandler: CacheFileHandler,
    private val profileNetwork: ProfileNetwork
) : AvatarRepository {

    override suspend fun loadAvatarFromCache(userId: String): ByteArray? {
        return cacheFileHandler.getFileData(name = "$AVATAR_FILE_NAME$userId$FILE_TYPE")
    }

    override suspend fun saveAvatarToCache(userId: String, avatar: ByteArray?) {
        cacheFileHandler.saveFile(name = "$AVATAR_FILE_NAME$userId$FILE_TYPE", data = avatar)
    }

    override suspend fun downloadAvatar(avatarUrl: String): ByteArray? {
        return profileNetwork.downloadAvatar(avatarUrl)
    }

    companion object {
        private const val AVATAR_FILE_NAME = "avatar_"
        private const val FILE_TYPE = ".jpg"
    }
}
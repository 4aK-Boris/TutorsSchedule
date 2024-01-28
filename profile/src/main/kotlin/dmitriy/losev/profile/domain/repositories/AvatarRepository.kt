package dmitriy.losev.profile.domain.repositories

interface AvatarRepository {

    suspend fun loadAvatarFromCache(userId: String): ByteArray?

    suspend fun saveAvatarToCache(userId: String, avatar: ByteArray?)

    suspend fun downloadAvatar(avatarUrl: String): ByteArray?
}
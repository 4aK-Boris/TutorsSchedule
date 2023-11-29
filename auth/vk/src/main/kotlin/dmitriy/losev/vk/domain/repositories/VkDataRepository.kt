package dmitriy.losev.vk.domain.repositories

import dmitriy.losev.vk.domain.models.VkUserData

interface VkDataRepository {

    suspend fun getVkUserData(userId: Long, token: String, email: String?): VkUserData
}
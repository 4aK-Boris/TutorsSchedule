package dmitriy.losev.vk.domain.usecases

import dmitriy.losev.vk.core.VkBaseUseCase
import dmitriy.losev.vk.domain.models.VkUserData
import dmitriy.losev.vk.domain.repositories.VkDataRepository

class VkUserDataUseCase(private val vkUserDataRepository: VkDataRepository) : VkBaseUseCase() {

    suspend fun getVkUserData(userId: Long, token: String, email: String?): VkUserData {
        return vkUserDataRepository.getVkUserData(userId, token, email)
    }
}
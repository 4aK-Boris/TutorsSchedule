package dmitriy.losev.vk.data.repositories

import dmitriy.losev.vk.data.mappers.VkUserDataMapper
import dmitriy.losev.vk.data.network.VkNetwork
import dmitriy.losev.vk.domain.models.VkUserData
import dmitriy.losev.vk.domain.repositories.VkDataRepository

class VkDataRepositoryImpl(
    private val network: VkNetwork,
    private val vkUserDataMapper: VkUserDataMapper
) : VkDataRepository {

    override suspend fun getVkUserData(userId: Long, token: String, email: String?): VkUserData {
        return network.getVkUserData(userId, token).response.first().let { vkUserInfoDTO -> vkUserDataMapper.map(value = vkUserInfoDTO, token, email) }
    }
}
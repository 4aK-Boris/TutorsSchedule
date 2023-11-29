package dmitriy.losev.vk.data.repositories

import dmitriy.losev.firebase.data.mappers.FirebaseTokenMapper
import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.vk.data.mappers.VkUserDataMapper
import dmitriy.losev.vk.data.network.VkNetwork
import dmitriy.losev.vk.domain.models.VkUserData
import dmitriy.losev.vk.domain.repositories.VkTokenRepository

class VkTokenRepositoryImpl(
    private val network: VkNetwork,
    private val vkUserDataMapper: VkUserDataMapper,
    private val firebaseTokenMapper: FirebaseTokenMapper
) : VkTokenRepository {

    override suspend fun getFirebaseToken(vkUserData: VkUserData): FirebaseToken {
        return firebaseTokenMapper.map(value = network.getFirebaseToken(vkUserDataMapper.map(value = vkUserData)))
    }
}
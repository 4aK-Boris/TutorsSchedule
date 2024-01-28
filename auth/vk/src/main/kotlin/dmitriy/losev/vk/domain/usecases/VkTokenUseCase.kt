package dmitriy.losev.vk.domain.usecases

import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.vk.core.VkBaseUseCase
import dmitriy.losev.vk.domain.models.VkUserData
import dmitriy.losev.vk.domain.repositories.VkTokenRepository

class VkTokenUseCase(private val vkTokenRepository: VkTokenRepository) : VkBaseUseCase() {

    suspend fun getToken(vkUserData: VkUserData): FirebaseToken {
        return vkTokenRepository.getFirebaseToken(vkUserData)
    }
}
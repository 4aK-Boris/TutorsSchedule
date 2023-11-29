package dmitriy.losev.vk.data.network

import dmitriy.losev.firebase.data.dto.FirebaseTokenDTO
import dmitriy.losev.vk.data.dto.VkAuthDataDTO
import dmitriy.losev.vk.data.dto.VkResponseDTO

interface VkNetwork {

    suspend fun getVkUserData(userId: Long, token: String): VkResponseDTO

    suspend fun getFirebaseToken(vkAuthInfoDTO: VkAuthDataDTO): FirebaseTokenDTO
}
package dmitriy.losev.vk.domain.repositories

import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.vk.domain.models.VkUserData

interface VkTokenRepository {

    suspend fun getFirebaseToken(vkUserData: VkUserData): FirebaseToken
}
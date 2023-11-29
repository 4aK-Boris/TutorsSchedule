package dmitriy.losev.vk.data.network

import dmitriy.losev.firebase.data.dto.FirebaseTokenDTO
import dmitriy.losev.network.BASE_URL
import dmitriy.losev.network.KtorClient
import dmitriy.losev.vk.data.dto.VkAuthDataDTO
import dmitriy.losev.vk.data.dto.VkResponseDTO

class VkNetworkImpl(private val client: KtorClient): VkNetwork {

    override suspend fun getVkUserData(userId: Long, token: String): VkResponseDTO {
        return client.get(url = USER_INFO_URL, params = createParams(userId = userId, token = token))
    }

    override suspend fun getFirebaseToken(vkAuthInfoDTO: VkAuthDataDTO): FirebaseTokenDTO {
        return client.post(url = BASE_URL + VK_FIREBASE_TOKEN, body = vkAuthInfoDTO)
    }

    private fun createParams(userId: Long, token: String): Map<String, Any> {
        return mapOf(
            USER_IDS to userId,
            FIELDS to PHOTO,
            TOKEN to token,
            API_VERSION to API_VERSION_VALUE
        )
    }

    companion object {

        private const val USER_INFO_URL = "https://api.vk.com/method/users.get"

        private const val USER_IDS = "user_ids"
        private const val FIELDS = "fields"
        private const val PHOTO = "photo_100"
        private const val TOKEN = "access_token"
        private const val API_VERSION = "v"
        private const val API_VERSION_VALUE = 5.131

        private const val VK_FIREBASE_TOKEN = "/token/vk"
    }
}
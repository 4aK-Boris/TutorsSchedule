package dmitriy.losev.vk.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class VkResponseDTO(
    val response: List<VkUserDataDTO>
)

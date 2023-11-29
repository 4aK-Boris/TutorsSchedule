package dmitriy.losev.vk.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VkUserDataDTO(

    val id: Long,

    @SerialName(value = "photo_100")
    val photoUrl: String?,

    @SerialName(value = "first_name")
    val firstName: String,

    @SerialName(value = "last_name")
    val lastName: String,

    @SerialName(value = "can_access_closed")
    val canAccessClosed: Boolean,

    @SerialName(value = "is_closed")
    val isClosed: Boolean
)

package dmitriy.losev.vk.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VkAuthDataDTO(

    @SerialName(value = "uid")
    val uId: Long,

    @SerialName(value = "token")
    val token: String,

    @SerialName(value = "first_name")
    val firstName: String,

    @SerialName(value = "last_name")
    val lastName: String,

    @SerialName(value = "email")
    val email: String?,

    @SerialName(value = "photo_url")
    val photoUrl: String?
)

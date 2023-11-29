package dmitriy.losev.vk.domain.models

data class VkUserData(
    val id: Long,
    val token: String,
    val photoUrl: String?,
    val firstName: String,
    val lastName: String,
    val email: String?
)
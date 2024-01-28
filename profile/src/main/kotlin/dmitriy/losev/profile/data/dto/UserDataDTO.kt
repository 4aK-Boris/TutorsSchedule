package dmitriy.losev.profile.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDataDTO(
    val avatarUri: String?,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?
)
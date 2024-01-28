package dmitriy.losev.profile.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FullUserDataDTO(
    val avatarUri: String?,
    val displayName: String?,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val email: String?,
    val phoneNumber: String?,
    val hasEmailAndPasswordChanged: Boolean,
    val isEmailVerified: Boolean
)

package dmitriy.losev.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserAvailableDTO(val isUserAvailable: Boolean)
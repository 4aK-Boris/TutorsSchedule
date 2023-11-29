package dmitriy.losev.yandex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YandexUserDataDTO(

    @SerialName(value = "iat")
    val issuedAt: Long,

    @SerialName(value = "jti")
    val jwtId: String,

    @SerialName(value = "exp")
    val expirationTime: Long,

    @SerialName(value = "iss")
    val issuer: String,

    @SerialName(value = "uid")
    val uId: Long,

    @SerialName(value = "login")
    val login: String,

    @SerialName(value = "psuid")
    val psuId: String,

    @SerialName(value = "name")
    val name: String,

    @SerialName(value = "email")
    val email: String,

    @SerialName(value = "gender")
    val gender: String,

    @SerialName(value = "display_name")
    val displayName: String,

    @SerialName(value = "avatar_id")
    val avatarId: String
)
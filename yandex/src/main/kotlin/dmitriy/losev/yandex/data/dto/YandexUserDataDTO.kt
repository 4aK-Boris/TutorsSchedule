package dmitriy.losev.yandex.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class YandexUserDataDTO(
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val emails: Array<String>,
    val defaultEmail: String,
    val realName: String,
    val isAvatarEmpty: Boolean,
    val defaultAvatarId: Int,
    val login: String,
    val oldSocialLogin: String,
    val sex: String?,
    val id: String,
    val clientId: String,
    val psuId: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YandexUserDataDTO

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (displayName != other.displayName) return false
        if (!emails.contentEquals(other.emails)) return false
        if (defaultEmail != other.defaultEmail) return false
        if (realName != other.realName) return false
        if (isAvatarEmpty != other.isAvatarEmpty) return false
        if (defaultAvatarId != other.defaultAvatarId) return false
        if (login != other.login) return false
        if (oldSocialLogin != other.oldSocialLogin) return false
        if (sex != other.sex) return false
        if (id != other.id) return false
        if (clientId != other.clientId) return false
        return psuId == other.psuId
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + emails.contentHashCode()
        result = 31 * result + defaultEmail.hashCode()
        result = 31 * result + realName.hashCode()
        result = 31 * result + isAvatarEmpty.hashCode()
        result = 31 * result + defaultAvatarId
        result = 31 * result + login.hashCode()
        result = 31 * result + oldSocialLogin.hashCode()
        result = 31 * result + (sex?.hashCode() ?: 0)
        result = 31 * result + id.hashCode()
        result = 31 * result + clientId.hashCode()
        result = 31 * result + psuId.hashCode()
        return result
    }
}

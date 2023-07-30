package dmitriy.losev.yandex.domain.models

data class YandexUserData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val isAvatarEmpty: Boolean,
    val avatarId: Int
)

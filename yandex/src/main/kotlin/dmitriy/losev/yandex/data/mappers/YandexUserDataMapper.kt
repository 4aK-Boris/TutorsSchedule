package dmitriy.losev.yandex.data.mappers

import dmitriy.losev.yandex.data.dto.YandexUserDataDTO
import dmitriy.losev.yandex.domain.models.YandexUserData

class YandexUserDataMapper {

    fun map(value: YandexUserDataDTO): YandexUserData {
        val (firstName, lastName) = value.displayName.split(' ')
        println(value.psuId)
        return YandexUserData(
            firstName = firstName,
            lastName = lastName,
            email = value.email,
            avatarId = value.avatarId.toIntOrNull()
        )
    }
}
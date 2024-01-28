package dmitriy.losev.profile.data.mappers

import dmitriy.losev.profile.data.dto.UserDataDTO
import dmitriy.losev.profile.domain.models.UserData

class UserDataMapper(private val uriMapper: UriMapper) {

    fun map(value: UserData): UserDataDTO {
        return UserDataDTO(
            avatarUri = uriMapper.map(value = value.avatarUri),
            displayName = value.displayName,
            phoneNumber = value.phoneNumber,
            email = value.email
        )
    }

    fun map(value: UserDataDTO?): UserData? {
        return value?.let {
            UserData(
                avatarUri = uriMapper.map(value = value.avatarUri),
                displayName = value.displayName,
                phoneNumber = value.phoneNumber,
                email = value.email
            )
        }
    }
}
package dmitriy.losev.profile.data.mappers

import dmitriy.losev.profile.data.dto.FullUserDataDTO
import dmitriy.losev.profile.domain.models.FullUserData

class FullUserDataMapper(private val uriMapper: UriMapper) {

    fun map(value: FullUserData): FullUserDataDTO {
        return FullUserDataDTO(
            avatarUri = uriMapper.map(value = value.avatarUri),
            displayName = value.displayName,
            firstName = value.firstName,
            lastName = value.lastName,
            patronymic = value.patronymic,
            email = value.email,
            phoneNumber = value.phoneNumber,
            hasEmailAndPasswordChanged = value.hasEmailAndPasswordChanged,
            isEmailVerified = value.isEmailVerified
        )
    }

    fun map(value: FullUserDataDTO?): FullUserData? {
        return value?.let {
            FullUserData(
                avatarUri = uriMapper.map(value = value.avatarUri),
                displayName = value.displayName,
                firstName = value.firstName,
                lastName = value.lastName,
                patronymic = value.patronymic,
                email = value.email,
                phoneNumber = value.phoneNumber,
                hasEmailAndPasswordChanged = value.hasEmailAndPasswordChanged,
                isEmailVerified = value.isEmailVerified
            )
        }
    }
}
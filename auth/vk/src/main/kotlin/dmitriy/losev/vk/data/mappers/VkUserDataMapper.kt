package dmitriy.losev.vk.data.mappers

import dmitriy.losev.vk.data.dto.VkAuthDataDTO
import dmitriy.losev.vk.data.dto.VkUserDataDTO
import dmitriy.losev.vk.domain.models.VkUserData

class VkUserDataMapper {

    fun map(value: VkUserDataDTO, token: String, email: String?): VkUserData {
        return VkUserData(
            id = value.id,
            photoUrl = value.photoUrl,
            firstName = value.firstName,
            lastName = value.lastName,
            email = email,
            token = token
        )
    }

    fun map(value: VkUserData): VkAuthDataDTO {
        return VkAuthDataDTO(
            uId = value.id,
            email = value.email,
            firstName = value.firstName,
            lastName = value.lastName,
            token = value.token,
            photoUrl = value.photoUrl
        )
    }
}
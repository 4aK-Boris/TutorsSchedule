package dmitriy.losev.profile.data.mappers

import dmitriy.losev.profile.data.dto.UserAvailableDTO
import dmitriy.losev.profile.domain.models.UserAvailable

class UserAvailableMapper {

    fun map(value: UserAvailableDTO): UserAvailable {
        return UserAvailable(isUserAvailable = value.isUserAvailable)
    }
}
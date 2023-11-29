package dmitriy.losev.auth.data.mappers

import dmitriy.losev.auth.data.dto.UserAvailableDTO
import dmitriy.losev.auth.domain.models.UserAvailable

class UserAvailableMapper {

    fun map(value: UserAvailableDTO): UserAvailable {
        return UserAvailable(isUserAvailable = value.isUserAvailable)
    }
}
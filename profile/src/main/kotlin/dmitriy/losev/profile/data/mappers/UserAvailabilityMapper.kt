package dmitriy.losev.profile.data.mappers

import dmitriy.losev.profile.data.dto.UserAvailabilityDTO
import dmitriy.losev.profile.domain.models.UserAvailability

class UserAvailabilityMapper {

    fun map(value: UserAvailabilityDTO): UserAvailability {
        return UserAvailability(isUserAvailable = value.isUserAvailable)
    }
}
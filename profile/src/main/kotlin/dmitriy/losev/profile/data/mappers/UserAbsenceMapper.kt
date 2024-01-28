package dmitriy.losev.profile.data.mappers

import dmitriy.losev.profile.data.dto.UserAbsenceDTO
import dmitriy.losev.profile.domain.models.UserAbsence

class UserAbsenceMapper {

    fun map(value: UserAbsenceDTO): UserAbsence {
        return UserAbsence(isUserAbsence = value.isUserAbsence)
    }
}
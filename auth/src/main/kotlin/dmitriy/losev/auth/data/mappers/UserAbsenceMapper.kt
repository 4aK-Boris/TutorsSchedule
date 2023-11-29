package dmitriy.losev.auth.data.mappers

import dmitriy.losev.auth.data.dto.UserAbsenceDTO
import dmitriy.losev.auth.domain.models.UserAbsence

class UserAbsenceMapper {

    fun map(value: UserAbsenceDTO): UserAbsence {
        return UserAbsence(isUserAbsence = value.isUserAbsence)
    }
}
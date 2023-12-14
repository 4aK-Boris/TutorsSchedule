package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.core.toNotNull
import dmitriy.losev.firebase.core.toNullable
import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.domain.models.Group

class GroupMapper {

    fun map(value: Group): GroupDTO {
        return GroupDTO(
            id = value.id,
            name = value.name.toNullable()
        )
    }

    fun map(value: GroupDTO): Group {
        return Group(
            id = value.id,
            name = value.name.toNotNull()
        )
    }
}
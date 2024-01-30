package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNullable
import dmitriy.losev.firebase.data.dto.GroupDTO

class GroupMapper {

    fun map(value: Group): GroupDTO {
        return GroupDTO(
            id = value.id,
            name = value.name.toNullable(),
            groupType = value.groupType.name
        )
    }

    fun map(value: GroupDTO): Group {
        return Group(
            id = value.id.toNotNull(),
            name = value.name.toNotNull(),
            groupType = GroupType.valueOf(value = value.groupType)
        )
    }
}
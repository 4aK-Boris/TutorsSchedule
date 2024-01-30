package dmitriy.losev.database.data.mappers

import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.database.data.entity.GroupEntity

class GroupMapper {

    fun map(value: Group): GroupEntity {
        return GroupEntity(
            id = value.id,
            name = value.name,
            groupType = value.groupType.name
        )
    }

    fun map(value: GroupEntity?): Group? {
        return value?.let {
            Group(
                id = value.id,
                name = value.name,
                groupType = GroupType.valueOf(value = value.groupType)
            )
        }
    }
}
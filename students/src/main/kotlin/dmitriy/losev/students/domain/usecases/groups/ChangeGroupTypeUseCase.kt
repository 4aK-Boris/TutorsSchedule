package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.students.core.StudentsBaseUseCase

class ChangeGroupTypeUseCase(private val updateGroupUseCase: UpdateGroupUseCase) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun changeGroupType(group: Group, groupType: GroupType): Group {
        val newGroup = group.copy(groupType = groupType)
        updateGroupUseCase.updateGroup(group = newGroup)
        return newGroup
    }
}
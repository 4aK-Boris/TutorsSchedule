package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.students.core.StudentsBaseUseCase

class GroupBringBackFromArchiveUseCase(private val changeGroupTypeUseCase: ChangeGroupTypeUseCase): StudentsBaseUseCase() {

    suspend fun bringItBackFromArchive(group: Group): Group {
        return changeGroupTypeUseCase.changeGroupType(group = group, groupType = GroupType.ACTIVE)
    }
}
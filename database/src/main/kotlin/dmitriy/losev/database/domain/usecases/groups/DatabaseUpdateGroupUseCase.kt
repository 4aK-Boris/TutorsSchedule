package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseUpdateGroupUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun updateGroup(group: Group) {
        groupsRepository.updateGroup(group)
    }
}
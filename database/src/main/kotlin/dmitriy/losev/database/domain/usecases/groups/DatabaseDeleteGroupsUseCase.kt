package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseDeleteGroupsUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun deleteGroups(groups: List<Group>) {
        groupsRepository.deleteGroups(groups)
    }
}
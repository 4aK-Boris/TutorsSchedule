package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseSaveGroupsUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun saveGroups(groups: List<Group>) {
        groupsRepository.saveGroups(groups)
    }
}
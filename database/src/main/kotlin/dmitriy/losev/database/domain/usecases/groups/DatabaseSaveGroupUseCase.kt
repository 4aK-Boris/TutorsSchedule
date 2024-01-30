package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseSaveGroupUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun saveGroup(group: Group) {
        groupsRepository.saveGroup(group)
    }
}
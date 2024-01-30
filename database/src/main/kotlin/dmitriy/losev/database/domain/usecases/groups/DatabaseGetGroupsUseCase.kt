package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseGetGroupsUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun getGroups(): List<Group> {
        return groupsRepository.getGroups()
    }
}
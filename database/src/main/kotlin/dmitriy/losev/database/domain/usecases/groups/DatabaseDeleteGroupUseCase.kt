package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseDeleteGroupUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun deleteGroup(groupId: String) {
        groupsRepository.deleteGroup(groupId)
    }
}
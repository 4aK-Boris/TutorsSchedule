package dmitriy.losev.database.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class DatabaseGetGroupUseCase(private val groupsRepository: GroupsRepository): DatabaseBaseUseCase() {

    suspend fun getGroup(groupId: String): Group? {
        return groupsRepository.getGroup(groupId)
    }
}
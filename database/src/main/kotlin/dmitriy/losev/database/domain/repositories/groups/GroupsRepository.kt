package dmitriy.losev.database.domain.repositories.groups

import dmitriy.losev.core.models.Group

interface GroupsRepository {

    suspend fun addGroup(group: Group)

    suspend fun updateGroup(group: Group)

    suspend fun saveGroup(group: Group)

    suspend fun saveGroups(groups: List<Group>)

    suspend fun deleteGroup(groupId: String)

    suspend fun deleteGroups(groups: List<Group>)

    suspend fun getGroup(groupId: String): Group?

    suspend fun getGroups(): List<Group>
}
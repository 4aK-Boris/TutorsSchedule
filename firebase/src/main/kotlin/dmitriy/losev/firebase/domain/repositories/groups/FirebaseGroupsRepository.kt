package dmitriy.losev.firebase.domain.repositories.groups

import dmitriy.losev.core.models.Group

interface FirebaseGroupsRepository {

    suspend fun addGroup(teacherId: String, group: Group): String?

    suspend fun getGroup(teacherId: String, groupId: String): Group?

    suspend fun getGroups(teacherId: String): List<Group>

    suspend fun deleteGroup(teacherId: String, groupId: String)

    suspend fun updateGroup(teacherId: String, groupId: String, group: Group)
}
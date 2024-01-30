package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupsRepositoryImpl(
    private val reference: DatabaseReference,
    private val groupMapper: GroupMapper
) : FirebaseGroupsRepository {

    override suspend fun addGroup(teacherId: String, group: Group): String? {
        val groupDTO = groupMapper.map(value = group)
        getGroupsReference(teacherId).push().apply {
            setValue(groupDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getGroup(teacherId: String, groupId: String): Group? {
        return getGroupsReference(teacherId).child(groupId).get().await().getValue(GroupDTO::class.java)?.let { groupDTO ->
            groupMapper.map(value = groupDTO)
        }
    }

    override suspend fun getGroups(teacherId: String): List<Group> {
        return getGroupsReference(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(GroupDTO::class.java)?.let { groupDTO ->  groupMapper.map(value = groupDTO) }
        }
    }

    override suspend fun deleteGroup(teacherId: String, groupId: String) {
        getGroupsReference(teacherId).child(groupId).removeValue().await()
    }

    override suspend fun updateGroup(teacherId: String, groupId: String, group: Group) {
        getGroupsReference(teacherId).updateChildren(mapOf(groupId to groupMapper.map(value = group))).await()
    }

    private fun getGroupsReference(teacherId: String): DatabaseReference {
        return reference.child(teacherId).child(GROUPS)
    }
}
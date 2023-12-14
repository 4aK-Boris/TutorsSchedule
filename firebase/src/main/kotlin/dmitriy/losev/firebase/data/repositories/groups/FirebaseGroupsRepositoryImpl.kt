package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.firebase.domain.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupsRepositoryImpl(
    reference: DatabaseReference,
    private val groupMapper: GroupMapper
) : FirebaseGroupsRepository {

    private val groups by lazy { reference.child(GROUPS) }

    override suspend fun addGroup(teacherId: String, group: Group): String? {
        val groupDTO = groupMapper.map(value = group)
        groups.child(teacherId).push().apply {
            setValue(groupDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getGroup(teacherId: String, groupId: String): Group? {
        return groups.child(teacherId).child(groupId).get().await().getValue(GroupDTO::class.java)?.let { groupDTO ->
            groupMapper.map(value = groupDTO)
        }
    }

    override suspend fun getGroups(teacherId: String): List<Group> {
        return groups.child(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(GroupDTO::class.java)?.let { groupDTO ->  groupMapper.map(value = groupDTO) }
        }
    }

    override suspend fun deleteGroup(teacherId: String, groupId: String) {
        groups.child(teacherId).child(groupId).removeValue().await()
    }

    override suspend fun updateGroup(teacherId: String, groupId: String, group: Group) {
        groups.child(teacherId).updateChildren(mapOf(groupId to groupMapper.map(value = group))).await()
    }
}
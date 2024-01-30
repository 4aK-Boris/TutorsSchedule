package dmitriy.losev.firebase.core.usecases.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.core.models.Group
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseGroupsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val groupMapper by inject<GroupMapper>()

    private val groupsReference by lazy { reference.child(GROUPS) }
    private val userUID by lazy { auth.currentUser?.uid ?: throw UserNotAuthorizationException() }

    protected suspend fun addGroup() {
        groupsReference.child(userUID).child(GROUP_ID).setValue(groupMapper.map(value = group)).await()
    }

    protected suspend fun addGroup(id: String) {
        groupsReference.child(userUID).child(id).setValue(groupMapper.map(value = group.copy(id = id))).await()
    }

    protected suspend fun deleteGroups() {
        groupsReference.child(userUID).get().await().children.forEach { group ->
            group.ref.removeValue().await()
        }
    }

    protected suspend fun getGroup(): Group? {
        return groupsReference.child(userUID).child(GROUP_ID).get().await().getValue(GroupDTO::class.java)?.let { groupDTO ->
            groupMapper.map(value = groupDTO)
        }
    }

    protected suspend fun getGroup(key: String): Group? {
        return groupsReference.child(userUID).child(key).get().await().getValue(GroupDTO::class.java)?.let { groupDTO ->
            groupMapper.map(value = groupDTO)
        }
    }

    protected suspend fun hasGroup(): Boolean {
        return groupsReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val GROUP_ID = "702yn476f32478n632584c320496732c42"

        const val NAME = "Группа для Насти"

        val group = Group(id = GROUP_ID, name = NAME)
    }
}
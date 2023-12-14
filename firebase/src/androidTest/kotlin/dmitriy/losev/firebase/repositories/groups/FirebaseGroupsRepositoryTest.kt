package dmitriy.losev.firebase.repositories.groups

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.firebase.domain.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseGroupsRepositoryTest: BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val groupMapper by inject<GroupMapper>()

    private val groupsReference by lazy { reference.child(GROUPS) }

    private val firebaseGroupsRepository by inject<FirebaseGroupsRepository>()

    override suspend fun tearDown() {
        deleteGroups()
    }

    @Test
    fun testAddGroup(): Unit = runBlocking {

        val key = firebaseGroupsRepository.addGroup(userUID, group)!!

        val hasGroup = hasGroup()

        assertTrue(hasGroup)

        val actualResult = getGroup(key)

        assertEquals(group.copy(id = key), actualResult)
    }

    @Test
    fun testUpdateGroup(): Unit = runBlocking {

        addGroup()

        val hasGroup = hasGroup()

        assertTrue(hasGroup)

        val newGroup = group.copy(name = NEW_NAME)

        firebaseGroupsRepository.updateGroup(userUID, GROUP_ID, newGroup)

        val actualResult = getGroup()

        assertEquals(newGroup, actualResult)
    }

    @Test
    fun testDeleteGroup(): Unit = runBlocking {

        addGroup()

        var hasGroup = hasGroup()

        assertTrue(hasGroup)

        firebaseGroupsRepository.deleteGroup(userUID, GROUP_ID)

        hasGroup = hasGroup()

        assertFalse(hasGroup)
    }

    @Test
    fun testGetGroup(): Unit = runBlocking {

        addGroup()

        val hasGroup = hasGroup()

        assertTrue(hasGroup)

        val actualResult = firebaseGroupsRepository.getGroup(userUID, GROUP_ID)

        assertEquals(group, actualResult)
    }

    @Test
    fun testGetGroups(): Unit = runBlocking {

        val size = 3

        repeat(size) { index ->
            addGroup(id = "${GROUP_ID}$index")
        }

        val actualResult = firebaseGroupsRepository.getGroups(userUID)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, Group ->
            assertEquals("${GROUP_ID}$index", Group.id)
            assertEquals(NAME, Group.name)
        }
    }


    private suspend fun addGroup() {
        addGroup(id = GROUP_ID)
    }

    private suspend fun addGroup(id: String) {
        groupsReference.child(userUID).child(id).setValue(groupMapper.map(value = group.copy(id = id))).await()
    }

    private suspend fun deleteGroups() {
        groupsReference.child(userUID).get().await().children.forEach { dataSnapShot ->
            dataSnapShot.ref.removeValue().await()
        }
    }

    private suspend fun getGroup(): Group? {
        return getGroup(key = GROUP_ID)
    }

    private suspend fun getGroup(key: String): Group? {
        return groupsReference.child(userUID).child(key).get().await().getValue(GroupDTO::class.java)?.let { groupDTO ->
            groupMapper.map(value = groupDTO)
        }
    }

    private suspend fun hasGroup(): Boolean {
        return groupsReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val NAME = "Группа для Насти"
        private const val NEW_NAME = "Группа для Иры"
        
        private const val GROUP_ID = "4324324324324234v8324324324v32"

        private val group = Group(id = GROUP_ID, name = NAME)
    }
} 
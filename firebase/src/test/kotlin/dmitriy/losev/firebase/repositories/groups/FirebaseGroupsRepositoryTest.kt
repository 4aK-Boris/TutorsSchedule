package dmitriy.losev.firebase.repositories.groups

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.data.dto.GroupDTO
import dmitriy.losev.firebase.data.mappers.GroupMapper
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupsRepositoryImpl
import dmitriy.losev.firebase.domain.models.Group
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FirebaseGroupsRepositoryTest {

    private val groupsReference = mockk<DatabaseReference>()
    private val teacherReference = mockk<DatabaseReference>()
    private val groupReference = mockk<DatabaseReference>()
    private val group = mockk<Group>()
    private val groupDTO = mockk<GroupDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val groupMapper = mockk<GroupMapper>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseGroupsRepository = FirebaseGroupsRepositoryImpl(reference, groupMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(GROUPS) } returns groupsReference
        every { groupsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.child(GROUP_ID) } returns groupReference
        every { groupMapper.map(value = group) } returns groupDTO
        every { groupMapper.map(value = groupDTO) } returns group
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddGroup(): Unit = runBlocking {

        every { teacherReference.push() } returns groupReference
        every { groupReference.key } returns KEY
        every { groupDTO.copy(id = KEY) } returns groupDTO
        every { groupReference.setValue(groupDTO) } returns task

        val actualResult = firebaseGroupsRepository.addGroup(TEACHER_ID, group)

        verifySequence {
            groupMapper.map(value = group)
            reference.child(GROUPS)
            groupsReference.child(TEACHER_ID)
            teacherReference.push()
            groupReference.key
            groupReference.setValue(groupDTO)
            groupReference.key
        }

        assertEquals(KEY, actualResult)
    }

    @Test
    fun testGetNotNullGroup(): Unit = runBlocking {

        every { groupReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(GroupDTO::class.java) } returns groupDTO

        val actualResult = firebaseGroupsRepository.getGroup(TEACHER_ID, GROUP_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(TEACHER_ID)
            teacherReference.child(GROUP_ID)
            groupReference.get()
            dataSnapshotResult.getValue(GroupDTO::class.java)
            groupMapper.map(value = groupDTO)
        }

        assertEquals(group, actualResult)
    }

    @Test
    fun testGetNullableGroup(): Unit = runBlocking {

        every { groupReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(GroupDTO::class.java) } returns null

        val actualResult = firebaseGroupsRepository.getGroup(TEACHER_ID, GROUP_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(TEACHER_ID)
            teacherReference.child(GROUP_ID)
            groupReference.get()
            dataSnapshotResult.getValue(GroupDTO::class.java)
        }

        verify { groupMapper.map(value = groupDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdateGroup(): Unit = runBlocking {

        every { teacherReference.updateChildren(mapOf(GROUP_ID to groupDTO)) } returns task

        firebaseGroupsRepository.updateGroup(TEACHER_ID, GROUP_ID, group)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(TEACHER_ID)
            groupMapper.map(value = group)
            teacherReference.updateChildren(mapOf(GROUP_ID to groupDTO))
        }
    }

    @Test
    fun testDeleteGroup(): Unit = runBlocking {

        every { teacherReference.child(GROUP_ID) } returns groupReference
        every { groupReference.removeValue() } returns task

        firebaseGroupsRepository.deleteGroup(TEACHER_ID, GROUP_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(TEACHER_ID)
            teacherReference.child(GROUP_ID)
            groupReference.removeValue()
        }
    }

    @Test
    fun testGetGroups(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { teacherReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(GroupDTO::class.java) } returns groupDTO

        val actualResult = firebaseGroupsRepository.getGroups(TEACHER_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(TEACHER_ID)
            teacherReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(GroupDTO::class.java)
            groupMapper.map(value = groupDTO)
            dataSnapshotResult.getValue(GroupDTO::class.java)
            groupMapper.map(value = groupDTO)
            dataSnapshotResult.getValue(GroupDTO::class.java)
            groupMapper.map(value = groupDTO)
        }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { student ->
            assertEquals(group, student)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val GROUP_ID = "group_id"
        private const val KEY = "key"
    }
}
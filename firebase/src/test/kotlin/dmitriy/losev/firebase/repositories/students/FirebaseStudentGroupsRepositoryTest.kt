package dmitriy.losev.firebase.repositories.students

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentGroupsRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifyOrder
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirebaseStudentGroupsRepositoryTest {

    private val studentGroupsReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val groupReference = mockk<DatabaseReference>(relaxed = true)
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseStudentGroupRepository = FirebaseStudentGroupsRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(STUDENTS).child(GROUPS) } returns studentGroupsReference
        every { studentGroupsReference.child(STUDENT_ID) } returns studentReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllGroups(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { studentReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns GROUP_ID

        val actualResult = firebaseStudentGroupRepository.getAllGroups(STUDENT_ID)

        verifyOrder {
            reference.child(STUDENTS).child(GROUPS)
            studentGroupsReference.child(STUDENT_ID)
            studentReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(Boolean::class.java)
            dataSnapshotResult.key
        }

        verify(exactly = listSnapshots.size) { dataSnapshotResult.getValue(Boolean::class.java) }
        verify(exactly = listSnapshots.size) { dataSnapshotResult.key }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { groupId ->
            assertEquals(GROUP_ID, groupId)
        }
    }

    @Test
    fun testAddGroup(): Unit = runBlocking {

        every { studentReference.child(GROUP_ID) } returns groupReference
        every { groupReference.setValue(true) } returns task

        firebaseStudentGroupRepository.addGroup(STUDENT_ID, GROUP_ID)

        verifySequence {
            reference.child(STUDENTS).child(GROUPS)
            studentGroupsReference.child(STUDENT_ID)
            studentReference.child(GROUP_ID)
            groupReference.setValue(true)
        }
    }

    @Test
    fun testRemoveGroup(): Unit = runBlocking {

        every { studentReference.child(GROUP_ID) } returns groupReference
        every { groupReference.removeValue() } returns task

        firebaseStudentGroupRepository.removeGroup(STUDENT_ID, GROUP_ID)

        verifySequence {
            reference.child(STUDENTS).child(GROUPS)
            studentGroupsReference.child(STUDENT_ID)
            studentReference.child(GROUP_ID)
            groupReference.removeValue()
        }
    }

    @Test
    fun removeAllGroups(): Unit = runBlocking {

        every { studentReference.removeValue() } returns task

        firebaseStudentGroupRepository.removeAllGroups(STUDENT_ID)

        verifySequence {
            reference.child(STUDENTS).child(GROUPS)
            studentGroupsReference.child(STUDENT_ID)
            studentReference.removeValue()
        }
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val GROUP_ID = "group_id"
    }
}
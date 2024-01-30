package dmitriy.losev.firebase.repositories.groups

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.repositories.groups.FirebaseGroupStudentsRepositoryImpl
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

class FirebaseGroupStudentsRepositoryTest {

    private val groupsReference = mockk<DatabaseReference>()
    private val groupReference = mockk<DatabaseReference>()
    private val studentsReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseGroupStudentRepository = FirebaseGroupStudentsRepositoryImpl(reference)

    @BeforeEach
    fun setUp() {
        every { reference.child(GROUPS) } returns groupsReference
        every { groupsReference.child(GROUP_ID) } returns groupReference
        every { groupReference.child(STUDENTS) } returns studentsReference
        every { studentsReference.child(STUDENT_ID) } returns studentReference
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAllStudents(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { studentsReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(Boolean::class.java) } returns true
        every { dataSnapshotResult.key } returns STUDENT_ID

        val actualResult = firebaseGroupStudentRepository.getStudents(GROUP_ID)

        verifyOrder {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(STUDENTS)
            studentsReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(Boolean::class.java)
            dataSnapshotResult.key
        }

        verify(exactly = listSnapshots.size) { dataSnapshotResult.getValue(Boolean::class.java) }
        verify(exactly = listSnapshots.size) { dataSnapshotResult.key }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { studentId ->
            assertEquals(STUDENT_ID, studentId)
        }
    }

    @Test
    fun testAddStudent(): Unit = runBlocking {

        every { studentReference.setValue(true) } returns task

        firebaseGroupStudentRepository.addStudent(GROUP_ID, STUDENT_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.setValue(true)
        }
    }

    @Test
    fun testRemoveStudent(): Unit = runBlocking {

        every { studentReference.removeValue() } returns task

        firebaseGroupStudentRepository.removeStudent(GROUP_ID, STUDENT_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(STUDENTS)
            studentsReference.child(STUDENT_ID)
            studentReference.removeValue()
        }
    }

    @Test
    fun removeAllStudents(): Unit = runBlocking {

        every { studentsReference.removeValue() } returns task

        firebaseGroupStudentRepository.removeStudents(GROUP_ID)

        verifySequence {
            reference.child(GROUPS)
            groupsReference.child(GROUP_ID)
            groupReference.child(STUDENTS)
            studentsReference.removeValue()
        }
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val STUDENT_ID = "student_id"
    }
}
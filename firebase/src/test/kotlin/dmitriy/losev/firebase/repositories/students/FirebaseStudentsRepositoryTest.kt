package dmitriy.losev.firebase.repositories.students

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.dto.SimpleStudentDTO
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.SimpleStudentMapper
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.data.repositories.students.FirebaseStudentsRepositoryImpl
import dmitriy.losev.core.models.SimpleStudent
import dmitriy.losev.core.models.Student
import io.mockk.Called
import io.mockk.coVerifySequence
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

class FirebaseStudentsRepositoryTest {

    private val studentsReference = mockk<DatabaseReference>()
    private val teacherReference = mockk<DatabaseReference>()
    private val studentReference = mockk<DatabaseReference>()
    private val student = mockk<Student>()
    private val simpleStudent = mockk<SimpleStudent>()
    private val studentDTO = mockk<StudentDTO>()
    private val simpleStudentDTO = mockk<SimpleStudentDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val studentMapper = mockk<StudentMapper>()
    private val simpleStudentMapper = mockk<SimpleStudentMapper>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseStudentsRepository = FirebaseStudentsRepositoryImpl(reference, studentMapper, simpleStudentMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(STUDENTS) } returns studentsReference
        every { studentsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.child(STUDENT_ID) } returns studentReference
        every { studentMapper.map(value = student) } returns studentDTO
        every { studentMapper.map(value = studentDTO) } returns student
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddStudent(): Unit = runBlocking {

        every { studentsReference.child(TEACHER_ID) } returns teacherReference
        every { teacherReference.push() } returns studentReference
        every { studentDTO.copy(id = any()) } returns studentDTO
        every { studentReference.key } returns KEY
        every { studentReference.setValue(studentDTO) } returns task

        firebaseStudentsRepository.addStudent(TEACHER_ID, student)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(TEACHER_ID)
            teacherReference.push()
            studentMapper.map(value = student)
            studentReference.key
            studentReference.setValue(studentDTO)
            studentReference.key
        }
    }

    @Test
    fun testGetNotNullStudent(): Unit = runBlocking {

        every { studentReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(StudentDTO::class.java) } returns studentDTO

        val actualResult = firebaseStudentsRepository.getStudent(TEACHER_ID, STUDENT_ID)

        coVerifySequence {
            reference.child(STUDENTS)
            studentsReference.child(TEACHER_ID)
            teacherReference.child(STUDENT_ID)
            studentReference.get()
            dataSnapshotResult.getValue(StudentDTO::class.java)
            studentMapper.map(value = studentDTO)
        }

        assertEquals(student, actualResult)
    }

    @Test
    fun testGetNullableStudent(): Unit = runBlocking {

        every { studentReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(StudentDTO::class.java) } returns null

        val actualResult = firebaseStudentsRepository.getStudent(TEACHER_ID, STUDENT_ID)

        coVerifySequence {
            reference.child(STUDENTS)
            studentsReference.child(TEACHER_ID)
            studentReference.get()
            dataSnapshotResult.getValue(StudentDTO::class.java)
        }

        verify { studentMapper.map(value = studentDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdateStudent(): Unit = runBlocking {

        every { teacherReference.updateChildren(mapOf(STUDENT_ID to studentDTO)) } returns task

        firebaseStudentsRepository.updateStudent(TEACHER_ID, STUDENT_ID, student)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(TEACHER_ID)
            studentMapper.map(value = student)
            teacherReference.updateChildren(mapOf(STUDENT_ID to studentDTO))
        }
    }

    @Test
    fun testDeleteStudent(): Unit = runBlocking {

        every { studentReference.removeValue() } returns task

        firebaseStudentsRepository.deleteStudent(TEACHER_ID, STUDENT_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(TEACHER_ID)
            teacherReference.child(STUDENT_ID)
            studentReference.removeValue()
        }
    }

    @Test
    fun testGetSimpleStudents(): Unit = runBlocking {

        val listDTO = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { teacherReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listDTO
        every { dataSnapshotResult.getValue(SimpleStudentDTO::class.java) } returns simpleStudentDTO
        every { simpleStudentMapper.map(value = simpleStudentDTO) } returns simpleStudent

        val actualResult = firebaseStudentsRepository.getActiveStudents(TEACHER_ID)

        verifySequence {
            reference.child(STUDENTS)
            studentsReference.child(TEACHER_ID)
            teacherReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(SimpleStudentDTO::class.java)
            simpleStudentMapper.map(value = simpleStudentDTO)
            dataSnapshotResult.getValue(SimpleStudentDTO::class.java)
            simpleStudentMapper.map(value = simpleStudentDTO)
            dataSnapshotResult.getValue(SimpleStudentDTO::class.java)
            simpleStudentMapper.map(value = simpleStudentDTO)
        }

        assertEquals(listDTO.size, actualResult.size)

        actualResult.forEach { student ->
            assertEquals(simpleStudent, student)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val STUDENT_ID = "student_id"
        private const val KEY = "key"
    }
}
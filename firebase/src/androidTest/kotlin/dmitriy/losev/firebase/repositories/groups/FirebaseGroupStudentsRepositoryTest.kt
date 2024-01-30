package dmitriy.losev.firebase.repositories.groups

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseGroupStudentsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val studentsReference by lazy { reference.child(GROUPS).child(GROUP_ID).child(STUDENTS) }

    private val firebaseGroupStudentsRepository by inject<FirebaseGroupStudentsRepository>()

    override suspend fun tearDown() {
        deleteStudents()
    }

    @Test
    fun testGetAllStudents(): Unit = runBlocking {

        val count = 10

        addStudents(count = count)

        val actualResult = firebaseGroupStudentsRepository.getStudents(GROUP_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, studentId ->
            assertEquals("${STUDENT_ID}-$index", studentId)
        }
    }

    @Test
    fun testAddStudent(): Unit = runBlocking {

        firebaseGroupStudentsRepository.addStudent(GROUP_ID, STUDENT_ID)

        val hasStudent = hasStudent()

        assertTrue(hasStudent)

        val actualResult = getStudent()

        assertEquals(STUDENT_ID, actualResult)
    }

    @Test
    fun testRemoveStudent(): Unit = runBlocking {

        addStudent()

        var hasStudent = hasStudent()

        assertTrue(hasStudent)

        firebaseGroupStudentsRepository.removeStudent(GROUP_ID, STUDENT_ID)

        hasStudent = hasStudent()

        assertFalse(hasStudent)
    }

    @Test
    fun removeAllStudents(): Unit = runBlocking {

        val count = 10

        addStudents(count)

        var hasStudents = hasStudents()

        assertTrue(hasStudents)

        firebaseGroupStudentsRepository.removeStudents(GROUP_ID)

        hasStudents = hasStudents()

        assertFalse(hasStudents)
    }


    private suspend fun addStudent() {
        addStudent(id = STUDENT_ID)
    }

    private suspend fun addStudent(id: String) {
        studentsReference.child(id).setValue(true).await()
    }

    private suspend fun addStudents(count: Int) {
        repeat(count) { index ->
            addStudent(id = "$STUDENT_ID-$index")
        }
    }

    private suspend fun deleteStudents() {
        studentsReference.removeValue().await()
    }

    private suspend fun getStudent(): String? {
        return getStudent(key = STUDENT_ID)
    }

    private suspend fun getStudent(key: String): String? {
        val hasStudentInGroup = studentsReference.child(key).get().await().getValue(Boolean::class.java)
        return if (hasStudentInGroup == true) {
            studentsReference.child(key).key
        } else {
            null
        }
    }

    private suspend fun hasStudent(): Boolean {
        return studentsReference.get().await().children.find { dataSnapshot ->
            dataSnapshot.key == STUDENT_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasStudents(): Boolean {
        return studentsReference.get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val GROUP_ID = "4324324324324234v8324324324v32"
        private const val STUDENT_ID = "d9c4983m24382c7432m748320-432"
    }
}
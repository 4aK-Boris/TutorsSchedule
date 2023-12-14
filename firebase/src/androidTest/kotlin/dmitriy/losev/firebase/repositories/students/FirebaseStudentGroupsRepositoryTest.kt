package dmitriy.losev.firebase.repositories.students

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseStudentGroupsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val studentGroupsReference by lazy { reference.child(STUDENTS).child(GROUPS) }

    private val firebaseStudentGroupsRepository by inject<FirebaseStudentGroupsRepository>()

    override suspend fun tearDown() {
        deleteGroups()
    }

    @Test
    fun testGetAllGroups(): Unit = runBlocking {

        val count = 10

        addGroups(count = count)

        val actualResult = firebaseStudentGroupsRepository.getAllGroups(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, studentId ->
            assertEquals("${GROUP_ID}-$index", studentId)
        }
    }

    @Test
    fun testAddGroup(): Unit = runBlocking {

        firebaseStudentGroupsRepository.addGroup(STUDENT_ID, GROUP_ID)

        val hasGroup = hasGroup()

        assertTrue(hasGroup)

        val actualResult = getGroup()

        assertEquals(GROUP_ID, actualResult)
    }

    @Test
    fun testRemoveGroup(): Unit = runBlocking {

        addGroup()

        var hasGroup = hasGroup()

        assertTrue(hasGroup)

        firebaseStudentGroupsRepository.removeGroup(STUDENT_ID, GROUP_ID)

        hasGroup = hasGroup()

        assertFalse(hasGroup)
    }

    @Test
    fun removeAllGroups(): Unit = runBlocking {

        val count = 10

        addGroups(count)

        var hasGroups = hasGroups()

        assertTrue(hasGroups)

        firebaseStudentGroupsRepository.removeAllGroups(STUDENT_ID)

        hasGroups = hasGroups()

        assertFalse(hasGroups)
    }


    private suspend fun addGroup() {
        addGroup(id = GROUP_ID)
    }

    private suspend fun addGroup(id: String) {
        studentGroupsReference.child(STUDENT_ID).child(id).setValue(true).await()
    }

    private suspend fun addGroups(count: Int) {
        repeat(count) { index ->
            addGroup(id = "$GROUP_ID-$index")
        }
    }

    private suspend fun deleteGroups() {
        studentGroupsReference.child(STUDENT_ID).removeValue().await()
    }

    private suspend fun getGroup(): String? {
        return getGroup(key = GROUP_ID)
    }

    private suspend fun getGroup(key: String): String? {
        val hasGroup = studentGroupsReference.child(STUDENT_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasGroup == true) {
            studentGroupsReference.child(STUDENT_ID).child(key).key
        } else {
            null
        }
    }

    private suspend fun hasGroup(): Boolean {
        return studentGroupsReference.child(STUDENT_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == GROUP_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasGroups(): Boolean {
        return studentGroupsReference.child(STUDENT_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val STUDENT_ID = "d9c4983m24382c7432m748320-432"
        private const val GROUP_ID = "4324324324324234v8324324324v32"
    }
}
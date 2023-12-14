package dmitriy.losev.firebase.repositories.students

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseStudentTasksRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val studentTasksReference by lazy { reference.child(STUDENTS).child(TASKS) }

    private val firebaseStudentTasksRepository by inject<FirebaseStudentTasksRepository>()

    override suspend fun tearDown() {
        deleteTasks()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count = count)

        val actualResult = firebaseStudentTasksRepository.getAllTasks(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, studentId ->
            assertEquals("${TASK_ID}-$index", studentId)
        }
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseStudentTasksRepository.addTask(STUDENT_ID, TASK_ID)

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask()

        assertEquals(TASK_ID, actualResult)
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        addTask()

        var hasTask = hasTask()

        assertTrue(hasTask)

        firebaseStudentTasksRepository.removeTask(STUDENT_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count)

        var hasTasks = hasTasks()

        assertTrue(hasTasks)

        firebaseStudentTasksRepository.removeAllTasks(STUDENT_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }


    private suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    private suspend fun addTask(id: String) {
        studentTasksReference.child(STUDENT_ID).child(id).setValue(true).await()
    }

    private suspend fun addTasks(count: Int) {
        repeat(count) { index ->
            addTask(id = "$TASK_ID-$index")
        }
    }

    private suspend fun deleteTasks() {
        studentTasksReference.child(STUDENT_ID).removeValue().await()
    }

    private suspend fun getTask(): String? {
        return getTask(key = TASK_ID)
    }

    private suspend fun getTask(key: String): String? {
        val hasTask = studentTasksReference.child(STUDENT_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasTask == true) {
            studentTasksReference.child(STUDENT_ID).child(key).key
        } else {
            null
        }
    }

    private suspend fun hasTask(): Boolean {
        return studentTasksReference.child(STUDENT_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == TASK_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasTasks(): Boolean {
        return studentTasksReference.child(STUDENT_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val STUDENT_ID = "d9c4983m24382c7432m748320-432"
        private const val TASK_ID = "4324324324324234v8324324324v32"
    }
}
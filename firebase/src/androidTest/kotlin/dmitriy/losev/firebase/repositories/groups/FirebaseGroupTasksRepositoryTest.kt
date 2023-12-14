package dmitriy.losev.firebase.repositories.groups

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseGroupTasksRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val groupTasksReference by lazy { reference.child(GROUPS).child(TASKS) }

    private val firebaseGroupTasksRepository by inject<FirebaseGroupTasksRepository>()

    override suspend fun tearDown() {
        deleteTasks()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count = count)

        val actualResult = firebaseGroupTasksRepository.getAllTasks(GROUP_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, taskId ->
            assertEquals("${TASK_ID}-$index", taskId)
        }
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseGroupTasksRepository.addTask(GROUP_ID, TASK_ID)

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

        firebaseGroupTasksRepository.removeTask(GROUP_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count)

        var hasTasks = hasTasks()

        assertTrue(hasTasks)

        firebaseGroupTasksRepository.removeAllTasks(GROUP_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }


    private suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    private suspend fun addTask(id: String) {
        groupTasksReference.child(GROUP_ID).child(id).setValue(true).await()
    }

    private suspend fun addTasks(count: Int) {
        repeat(count) { index ->
            addTask(id = "$TASK_ID-$index")
        }
    }

    private suspend fun deleteTasks() {
        groupTasksReference.child(GROUP_ID).removeValue().await()
    }

    private suspend fun getTask(): String? {
        return getTask(key = TASK_ID)
    }

    private suspend fun getTask(key: String): String? {
        val hasTaskInGroup = groupTasksReference.child(GROUP_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasTaskInGroup == true) {
            groupTasksReference.child(GROUP_ID).child(key).key
        } else {
            null
        }
    }

    private suspend fun hasTask(): Boolean {
        return groupTasksReference.child(GROUP_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == TASK_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasTasks(): Boolean {
        return groupTasksReference.child(GROUP_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val GROUP_ID = "4324324324324234v8324324324v32"
        private const val TASK_ID = "d9c4983m24382c7432m748320-432"
    }
}
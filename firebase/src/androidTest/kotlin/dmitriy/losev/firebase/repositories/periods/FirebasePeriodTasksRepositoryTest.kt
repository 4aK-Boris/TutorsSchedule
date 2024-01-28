package dmitriy.losev.firebase.repositories.periods

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebasePeriodTasksRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val tasksReference by lazy { reference.child(PERIODS).child(PERIOD_ID).child(TASKS) }

    private val firebasePeriodTasksRepository by inject<FirebasePeriodTasksRepository>()

    override suspend fun tearDown() {
        deleteTasks()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count = count)

        val actualResult = firebasePeriodTasksRepository.getAllTasks(PERIOD_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, taskId ->
            assertEquals("${TASK_ID}-$index", taskId)
        }
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebasePeriodTasksRepository.addTask(PERIOD_ID, TASK_ID)

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

        firebasePeriodTasksRepository.removeTask(PERIOD_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count)

        var hasTasks = hasTasks()

        assertTrue(hasTasks)

        firebasePeriodTasksRepository.removeAllTasks(PERIOD_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }


    private suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    private suspend fun addTask(id: String) {
        tasksReference.child(id).setValue(true).await()
    }

    private suspend fun addTasks(count: Int) {
        repeat(count) { index ->
            addTask(id = "$TASK_ID-$index")
        }
    }

    private suspend fun deleteTasks() {
        tasksReference.removeValue().await()
    }

    private suspend fun getTask(): String? {
        return getTask(key = TASK_ID)
    }

    private suspend fun getTask(key: String): String? {
        val hasTaskInPeriod = tasksReference.child(key).get().await().getValue(Boolean::class.java)
        return if (hasTaskInPeriod == true) {
            tasksReference.child(key).key
        } else {
            null
        }
    }

    private suspend fun hasTask(): Boolean {
        return tasksReference.get().await().children.find { dataSnapshot ->
            dataSnapshot.key == TASK_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasTasks(): Boolean {
        return tasksReference.get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val PERIOD_ID = "4324324324324234v8324324324v32"
        private const val TASK_ID = "d9c4983m24382c7432m748320-432"
    }
}
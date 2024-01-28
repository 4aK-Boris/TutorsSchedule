package dmitriy.losev.firebase.repositories.tasks

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.models.types.LessonStatus
import dmitriy.losev.firebase.domain.models.types.PaidStatus
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import java.time.ZonedDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseTasksRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val taskMapper by inject<TaskMapper>()

    private val taskReference by lazy { reference.child(TASKS) }

    private val firebaseTasksRepository by inject<FirebaseTasksRepository>()

    override suspend fun tearDown() {
        deleteTasks()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        val key = firebaseTasksRepository.addTask(userUID, task)!!

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask(key)

        equalTasks(task.copy(id = key), actualResult)
    }

    @Test
    fun testUpdateTask(): Unit = runBlocking {

        addTask()

        val hasTask = hasTask()

        assertTrue(hasTask)

        val newTask = task.copy(dateTime = ZonedDateTime.now(), status = NEW_STATUS, paidStatus = NEW_PAID_STATUS)

        firebaseTasksRepository.updateTask(userUID, TASK_ID, newTask)

        val actualResult = getTask()

        equalTasks(newTask, actualResult)
    }

    @Test
    fun testDeleteTask(): Unit = runBlocking {

        addTask()

        var hasTask = hasTask()

        assertTrue(hasTask)

        firebaseTasksRepository.deleteTask(userUID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }

    @Test
    fun testGetTask(): Unit = runBlocking {

        addTask()

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = firebaseTasksRepository.getTask(userUID, TASK_ID)

        equalTasks(task, actualResult)
    }

    @Test
    fun testGetTasks(): Unit = runBlocking {

        val size = 3

        repeat(size) { index ->
            addTask(id = "$TASK_ID$index")
        }

        val actualResult = firebaseTasksRepository.getTasks(userUID)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, manyTask ->
            equalTasks(task.copy(id = "$TASK_ID$index"), manyTask)
        }
    }

    @Test
    fun testGetTasksAfterTime(): Unit = runBlocking {

        val size = 10
        val time =  ZonedDateTime.now().minusDays(1).toEpochSecond().toDouble()

        repeat(size) { index ->
            addTask(id = "$TASK_ID$index")
        }

        val actualResult = firebaseTasksRepository.getTasksAfterTime(userUID, time)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, manyTask ->
            equalTasks(task.copy(id = "$TASK_ID$index"), manyTask)
        }
    }

    @Test
    fun testGetLimitTasksAfterTime(): Unit = runBlocking {

        val size = 10
        val count = 5
        val time =  ZonedDateTime.now().minusDays(1).toEpochSecond().toDouble()

        repeat(size) { index ->
            addTask(id = "$TASK_ID$index")
        }

        val actualResult = firebaseTasksRepository.getLimitTasksAfterTime(userUID, count, time)

        assertEquals(count, actualResult.size)
    }


    private fun equalTasks(expectedTask: Task, actualTask: Task?) {
        assertEquals(expectedTask.id, actualTask?.id)
        assertEquals(expectedTask.lessonId, actualTask?.lessonId)
        assertEquals(expectedTask.status, actualTask?.status)
        assertEquals(expectedTask.paidStatus, actualTask?.paidStatus)
        assertEquals(expectedTask.status, actualTask?.status)
        assertEquals(expectedTask.paidStatus, actualTask?.paidStatus)

        assertEquals(expectedTask.dateTime.year, actualTask?.dateTime?.year)
        assertEquals(expectedTask.dateTime.month, actualTask?.dateTime?.month)
        assertEquals(expectedTask.dateTime.dayOfMonth, actualTask?.dateTime?.dayOfMonth)
        assertEquals(expectedTask.dateTime.hour, actualTask?.dateTime?.hour)
        assertEquals(expectedTask.dateTime.minute, actualTask?.dateTime?.minute)
        assertEquals(expectedTask.dateTime.second, actualTask?.dateTime?.second)
    }

    private suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    private suspend fun addTask(id: String) {
        taskReference.child(userUID).child(id).setValue(taskMapper.map(value = task.copy(id = id))).await()
    }

    private suspend fun deleteTasks() {
        taskReference.child(userUID).get().await().children.forEach { dataSnapShot ->
            dataSnapShot.ref.removeValue().await()
        }
    }

    private suspend fun getTask(): Task? {
        return getTask(key = TASK_ID)
    }

    private suspend fun getTask(key: String): Task? {
        return taskReference.child(userUID).child(key).get().await().getValue(TaskDTO::class.java)?.let { taskDTO ->
            taskMapper.map(value = taskDTO)
        }
    }

    private suspend fun hasTask(): Boolean {
        return taskReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val TASK_ID = "cn8v275n0v8423v75v97234dwcewaewa"
        private const val LESSON_ID = "cm9458207n5843275c98c723m5098743m2805934vf5"
        private const val PERIOD_ID = "439348v72-4873243209489c320849032c8-094328"
        private const val STUDENT_OR_GROUP_ID = "v57489n57v3408957983457v843"
        private const val SUBJECT_ID = "345v3425435435gsvvarverarwe"

        private val STATUS = LessonStatus.PLANNED
        private val PAID_STATUS = PaidStatus.NO_PAID
        private val NEW_STATUS = LessonStatus.CANCELLED
        private val NEW_PAID_STATUS = PaidStatus.PAID

        private val dateTime = ZonedDateTime.now()

        private val task = Task(
            id = TASK_ID,
            lessonId = LESSON_ID,
            studentOrGroupId = STUDENT_OR_GROUP_ID,
            subjectId = SUBJECT_ID,
            periodId = PERIOD_ID,
            dateTime = dateTime,
            status = STATUS,
            paidStatus = PAID_STATUS
        )
    }
}
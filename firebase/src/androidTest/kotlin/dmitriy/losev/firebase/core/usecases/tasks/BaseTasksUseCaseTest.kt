package dmitriy.losev.firebase.core.usecases.tasks

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.models.types.LessonStatus
import dmitriy.losev.firebase.domain.models.types.PaidStatus
import kotlinx.coroutines.tasks.await
import org.koin.test.inject
import java.time.ZonedDateTime
import kotlin.test.assertEquals

abstract class BaseTasksUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val taskMapper by inject<TaskMapper>()

    private val tasksReference by lazy { reference.child(TASKS) }
    private val userUID by lazy { auth.currentUser?.uid ?: throw UserNotAuthorizationException() }

    protected suspend fun addTask() {
        tasksReference.child(userUID).child(TASK_ID).setValue(taskMapper.map(value = task)).await()
    }

    protected suspend fun addTask(id: String) {
        tasksReference.child(userUID).child(id).setValue(taskMapper.map(value = task.copy(id = id))).await()
    }

    protected suspend fun deleteTasks() {
        tasksReference.child(userUID).get().await().children.forEach { task ->
            task.ref.removeValue().await()
        }
    }

    protected suspend fun getTask(): Task? {
        return tasksReference.child(userUID).child(TASK_ID).get().await().getValue(TaskDTO::class.java)?.let { taskDTO ->
            taskMapper.map(value = taskDTO)
        }
    }

    protected suspend fun getTask(key: String): Task? {
        return tasksReference.child(userUID).child(key).get().await().getValue(TaskDTO::class.java)?.let { taskDTO ->
            taskMapper.map(value = taskDTO)
        }
    }

    protected suspend fun hasTask(): Boolean {
        return tasksReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    protected fun equalsTasks(expectedTask: Task, actualTask: Task?) {
        assertEquals(expectedTask.id, actualTask?.id)
        assertEquals(expectedTask.lessonId, actualTask?.lessonId)
        assertEquals(expectedTask.studentOrGroupId, actualTask?.studentOrGroupId)
        assertEquals(expectedTask.subjectId, actualTask?.subjectId)
        assertEquals(expectedTask.status, actualTask?.status)
        assertEquals(expectedTask.paidStatus, actualTask?.paidStatus)

        assertEquals(expectedTask.dateTime.year, actualTask?.dateTime?.year)
        assertEquals(expectedTask.dateTime.month, actualTask?.dateTime?.month)
        assertEquals(expectedTask.dateTime.dayOfMonth, actualTask?.dateTime?.dayOfMonth)
        assertEquals(expectedTask.dateTime.hour, actualTask?.dateTime?.hour)
        assertEquals(expectedTask.dateTime.minute, actualTask?.dateTime?.minute)
        assertEquals(expectedTask.dateTime.second, actualTask?.dateTime?.second)
    }

    companion object {

        const val TASK_ID = "702yn476f32478n632584c320496732c42"
        private const val LESSON_ID = "dwac2394083294-832904-83920432c8-0"
        private const val PERIOD_ID = "4827304928c382974mc8309274893278"
        private const val STUDENT_OR_GROUP_ID = "v57489n57v3408957983457v843"
        private const val SUBJECT_ID = "345v3425435435gsvvarverarwe"

        private val STATUS = LessonStatus.PLANNED
        private val PAID_STATUS = PaidStatus.PAID

        private val dateTime = ZonedDateTime.now()

        val task = Task(
            id = TASK_ID,
            lessonId = LESSON_ID,
            periodId = PERIOD_ID,
            studentOrGroupId = STUDENT_OR_GROUP_ID,
            subjectId = SUBJECT_ID,
            dateTime = dateTime,
            status = STATUS,
            paidStatus = PAID_STATUS
        )
    }
}
package dmitriy.losev.firebase.data.repositories.task

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.core.models.types.LessonStatus
import dmitriy.losev.core.models.types.PaidStatus
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseTasksRepositoryImpl(
    reference: DatabaseReference,
    private val taskMapper: TaskMapper,
): FirebaseTasksRepository {

    private val tasks by lazy { reference.child(TASKS) }

    override suspend fun addTask(teacherId: String, task: Task): String? {
        val taskDTO = taskMapper.map(value = task)
        tasks.child(teacherId).push().apply {
            setValue(taskDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getTask(teacherId: String, taskId: String): Task? {
        return tasks.child(teacherId).child(taskId).get().await().getValue(TaskDTO::class.java)?.let { taskDTO ->
            taskMapper.map(value = taskDTO)
        }
    }

    override suspend fun updateTask(teacherId: String, taskId: String, task: Task) {
        tasks.child(teacherId).updateChildren(mapOf(taskId to taskMapper.map(value = task))).await()
    }

    override suspend fun deleteTask(teacherId: String, taskId: String) {
        tasks.child(teacherId).child(taskId).removeValue().await()
    }

    override suspend fun getTasks(
        teacherId: String,
        count: Int?,
        time: Double?,
        lessonId: String?,
        periodId: String?,
        studentOrGroupId: String?,
        subjectId: String?,
        lessonStatus: LessonStatus?,
        paidStatus: PaidStatus?
    ): List<Task> {
        var query: Query = tasks.child(teacherId)

        lessonId?.let {
            query = query.orderByChild(LESSON_ID_PATH).equalTo(lessonId)
        }

        periodId?.let {
            query = query.orderByChild(PERIOD_ID_PATH).equalTo(periodId)
        }

        studentOrGroupId?.let {
            query = query.orderByChild(STUDENT_OR_GROUP_ID_PATH).equalTo(studentOrGroupId)
        }

        subjectId?.let {
            query = query.orderByChild(SUBJECT_ID_PATH).equalTo(subjectId)
        }

        lessonStatus?.let {
            query = query.orderByChild(LESSON_STATUS_PATH).equalTo(lessonStatus.name)
        }

        paidStatus?.let {
            query = query.orderByChild(PAID_STATUS_PATH).equalTo(paidStatus.name)
        }

        time?.let {
            query = query.orderByChild(DATE_TIME_PATH).equalTo(time)
        }

        count?.let {
            query = query.limitToFirst(count)
        }

        return query.get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(TaskDTO::class.java)?.let { taskDTO -> taskMapper.map(value = taskDTO) }
        }
    }

    companion object {
        private const val DATE_TIME_PATH = "/dateTime"
        private const val LESSON_ID_PATH = "/lessonId"
        private const val PERIOD_ID_PATH = "/periodId"
        private const val STUDENT_OR_GROUP_ID_PATH = "/studentOrGroupId"
        private const val SUBJECT_ID_PATH = "/subjectId"
        private const val LESSON_STATUS_PATH = "/status"
        private const val PAID_STATUS_PATH = "/paidStatus"
    }
}
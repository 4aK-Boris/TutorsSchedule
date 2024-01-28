package dmitriy.losev.firebase.domain.repositories.tasks

import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.core.models.types.LessonStatus
import dmitriy.losev.core.models.types.PaidStatus

interface FirebaseTasksRepository {

    suspend fun addTask(teacherId: String, task: Task): String?

    suspend fun getTask(teacherId: String, taskId: String): Task?

    suspend fun updateTask(teacherId: String, taskId: String, task: Task)

    suspend fun deleteTask(teacherId: String, taskId: String)

    suspend fun getTasks(
        teacherId: String,
        count: Int? = null,
        time: Double? = null,
        lessonId: String? = null,
        periodId: String? = null,
        studentOrGroupId: String? = null,
        subjectId: String? = null,
        lessonStatus: LessonStatus? = null,
        paidStatus: PaidStatus? = null
    ): List<Task>
}
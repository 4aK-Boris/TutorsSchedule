package dmitriy.losev.firebase.domain.repositories.tasks

import dmitriy.losev.firebase.domain.models.Task

interface FirebaseTasksRepository {

    suspend fun addTask(teacherId: String, task: Task): String?

    suspend fun getTask(teacherId: String, taskId: String): Task?

    suspend fun updateTask(teacherId: String, taskId: String, task: Task)

    suspend fun deleteTask(teacherId: String, taskId: String)

    suspend fun getTasks(teacherId: String): List<Task>

    suspend fun getTasksAfterTime(teacherId: String, time: Double): List<Task>

    suspend fun getLimitTasksAfterTime(teacherId: String, count: Int, time: Double): List<Task>
}
package dmitriy.losev.firebase.domain.repositories.groups

interface FirebaseGroupTasksRepository {

    suspend fun getTasks(teacherId: String, groupId: String): List<String>

    suspend fun addTask(teacherId: String, groupId: String, taskId: String)

    suspend fun removeTask(teacherId: String, groupId: String, taskId: String)

    suspend fun removeTasks(teacherId: String, groupId: String)
}
package dmitriy.losev.firebase.domain.repositories.groups

interface FirebaseGroupTasksRepository {

    suspend fun getAllTasks(groupId: String): List<String>

    suspend fun addTask(groupId: String, taskId: String)

    suspend fun removeTask(groupId: String, taskId: String)

    suspend fun removeAllTasks(groupId: String)
}
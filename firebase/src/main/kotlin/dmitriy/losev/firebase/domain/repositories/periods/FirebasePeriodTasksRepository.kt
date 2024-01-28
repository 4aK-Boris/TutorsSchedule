package dmitriy.losev.firebase.domain.repositories.periods

interface FirebasePeriodTasksRepository {

    suspend fun getAllTasks(periodId: String): List<String>

    suspend fun addTask(periodId: String, taskId: String)

    suspend fun removeTask(periodId: String, taskId: String)

    suspend fun removeAllTasks(periodId: String)
}
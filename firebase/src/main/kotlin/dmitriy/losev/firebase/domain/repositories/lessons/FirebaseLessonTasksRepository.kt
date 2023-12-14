package dmitriy.losev.firebase.domain.repositories.lessons

interface FirebaseLessonTasksRepository {

    suspend fun getAllTasks(lessonId: String): List<String>

    suspend fun addTask(lessonId: String, taskId: String)

    suspend fun removeTask(lessonId: String, taskId: String)

    suspend fun removeAllTasks(lessonId: String)
}
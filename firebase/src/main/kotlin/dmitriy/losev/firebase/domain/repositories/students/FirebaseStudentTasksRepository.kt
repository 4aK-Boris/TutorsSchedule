package dmitriy.losev.firebase.domain.repositories.students

interface FirebaseStudentTasksRepository {

    suspend fun getAllTasks(studentId: String): List<String>

    suspend fun addTask(studentId: String, taskId: String)

    suspend fun removeTask(studentId: String, taskId: String)

    suspend fun removeAllTasks(studentId: String)
}
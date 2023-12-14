package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentTasksRepositoryImpl(reference: DatabaseReference): FirebaseStudentTasksRepository {

    private val studentsTasks by lazy { reference.child(STUDENTS).child(TASKS) }
    override suspend fun getAllTasks(studentId: String): List<String> {
        return studentsTasks.child(studentId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(studentId: String, taskId: String) {
        studentsTasks.child(studentId).child(taskId).setValue(true)
    }

    override suspend fun removeTask(studentId: String, taskId: String) {
        studentsTasks.child(studentId).child(taskId).removeValue()
    }

    override suspend fun removeAllTasks(studentId: String) {
        studentsTasks.child(studentId).removeValue()
    }
}
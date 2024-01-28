package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentTasksRepositoryImpl(reference: DatabaseReference): FirebaseStudentTasksRepository {

    private val students by lazy { reference.child(STUDENTS) }
    override suspend fun getAllTasks(studentId: String): List<String> {
        return students.child(studentId).child(TASKS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun getLimitTasks(studentId: String, count: Int): List<String> {
        return students.child(studentId).child(TASKS).limitToFirst(count).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(studentId: String, taskId: String) {
        students.child(studentId).child(TASKS).child(taskId).setValue(true)
    }

    override suspend fun removeTask(studentId: String, taskId: String) {
        students.child(studentId).child(TASKS).child(taskId).removeValue()
    }

    override suspend fun removeAllTasks(studentId: String) {
        students.child(studentId).child(TASKS).removeValue()
    }
}
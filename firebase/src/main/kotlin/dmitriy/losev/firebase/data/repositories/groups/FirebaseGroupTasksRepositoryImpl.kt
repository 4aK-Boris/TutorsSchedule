package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupTasksRepositoryImpl(private val reference: DatabaseReference): FirebaseGroupTasksRepository {

    override suspend fun getTasks(teacherId: String, groupId: String): List<String> {
        return getGroupTasksReference(teacherId, groupId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(teacherId: String, groupId: String, taskId: String) {
        getGroupTasksReference(teacherId, groupId).child(taskId).setValue(true).await()
    }

    override suspend fun removeTask(teacherId: String, groupId: String, taskId: String) {
        getGroupTasksReference(teacherId, groupId).child(taskId).removeValue().await()
    }

    override suspend fun removeTasks(teacherId: String, groupId: String) {
        getGroupTasksReference(teacherId, groupId).removeValue().await()
    }

    private fun getGroupTasksReference(teacherId: String, groupId: String): DatabaseReference {
        return reference.child(teacherId).child(GROUPS).child(groupId).child(TASKS)
    }
}
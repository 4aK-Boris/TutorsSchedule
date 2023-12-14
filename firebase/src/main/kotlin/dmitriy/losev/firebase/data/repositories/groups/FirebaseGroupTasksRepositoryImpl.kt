package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupTasksRepositoryImpl(reference: DatabaseReference): FirebaseGroupTasksRepository {
    
    private val groupTasks by lazy { reference.child(GROUPS).child(TASKS) }
    override suspend fun getAllTasks(groupId: String): List<String> {
        return groupTasks.child(groupId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(groupId: String, taskId: String) {
        groupTasks.child(groupId).child(taskId).setValue(true).await()
    }

    override suspend fun removeTask(groupId: String, taskId: String) {
        groupTasks.child(groupId).child(taskId).removeValue().await()
    }

    override suspend fun removeAllTasks(groupId: String) {
        groupTasks.child(groupId).removeValue().await()
    }
}
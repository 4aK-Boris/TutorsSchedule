package dmitriy.losev.firebase.data.repositories.task

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.data.mappers.TaskMapper
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseTasksRepositoryImpl(
    reference: DatabaseReference,
    private val taskMapper: TaskMapper,
): FirebaseTasksRepository {

    private val tasks by lazy { reference.child(TASKS) }

    override suspend fun addTask(teacherId: String, task: Task): String? {
        val taskDTO = taskMapper.map(value = task)
        tasks.child(teacherId).push().apply {
            setValue(taskDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getTask(teacherId: String, taskId: String): Task? {
        return tasks.child(teacherId).child(taskId).get().await().getValue(TaskDTO::class.java)?.let { taskDTO ->
            taskMapper.map(value = taskDTO)
        }
    }

    override suspend fun updateTask(teacherId: String, taskId: String, task: Task) {
        tasks.child(teacherId).updateChildren(mapOf(taskId to taskMapper.map(value = task))).await()
    }

    override suspend fun deleteTask(teacherId: String, taskId: String) {
        tasks.child(teacherId).child(taskId).removeValue().await()
    }

    override suspend fun getTasks(teacherId: String): List<Task> {
        return tasks.child(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(TaskDTO::class.java)?.let { taskDTO -> taskMapper.map(value = taskDTO) }
        }
    }
}
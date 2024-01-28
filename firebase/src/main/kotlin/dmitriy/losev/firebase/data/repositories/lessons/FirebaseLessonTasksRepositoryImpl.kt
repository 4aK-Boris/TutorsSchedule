package dmitriy.losev.firebase.data.repositories.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseLessonTasksRepositoryImpl(reference: DatabaseReference): FirebaseLessonTasksRepository {

    private val tasks by lazy { reference.child(LESSONS) }

    override suspend fun getAllTasks(lessonId: String): List<String> {
        return tasks.child(lessonId).child(TASKS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(lessonId: String, taskId: String) {
        tasks.child(lessonId).child(TASKS).child(taskId).setValue(true)
    }

    override suspend fun removeTask(lessonId: String, taskId: String) {
        tasks.child(lessonId).child(TASKS).child(taskId).removeValue()
    }

    override suspend fun removeAllTasks(lessonId: String) {
        tasks.child(lessonId).child(TASKS).removeValue()
    }
}
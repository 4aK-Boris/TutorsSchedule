package dmitriy.losev.firebase.data.repositories.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository
import kotlinx.coroutines.tasks.await

class FirebaseLessonTasksRepositoryImpl(reference: DatabaseReference): FirebaseLessonTasksRepository {

    private val lessonTasks by lazy { reference.child(LESSONS).child(TASKS) }

    override suspend fun getAllTasks(lessonId: String): List<String> {
        return lessonTasks.child(lessonId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(lessonId: String, taskId: String) {
        lessonTasks.child(lessonId).child(taskId).setValue(true)
    }

    override suspend fun removeTask(lessonId: String, taskId: String) {
        lessonTasks.child(lessonId).child(taskId).removeValue()
    }

    override suspend fun removeAllTasks(lessonId: String) {
        lessonTasks.child(lessonId).removeValue()
    }
}
package dmitriy.losev.firebase.core.usecases.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.TASKS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseLessonTasksUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val lessonTasksReference by lazy { reference.child(LESSONS).child(TASKS) }

    protected suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    protected suspend fun addTask(id: String) {
        lessonTasksReference.child(LESSON_ID).child(id).setValue(true).await()
    }

    protected suspend fun addTasks(count: Int) {
        repeat(count) { index ->
            addTask(id = "$TASK_ID-$index")
        }
    }

    protected suspend fun deleteTasks() {
        lessonTasksReference.child(LESSON_ID).removeValue().await()
    }

    protected suspend fun getTask(): String? {
        return getTask(key = TASK_ID)
    }

    protected suspend fun getTask(key: String): String? {
        val hasTaskInLesson = lessonTasksReference.child(LESSON_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasTaskInLesson == true) {
            lessonTasksReference.child(LESSON_ID).child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasTask(): Boolean {
        return lessonTasksReference.child(LESSON_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == TASK_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasTasks(): Boolean {
        return lessonTasksReference.child(LESSON_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val LESSON_ID = "4324324324324234v8324324324v32"
        const val TASK_ID = "d9c4983m24382c7432m748320-432"
    }
}
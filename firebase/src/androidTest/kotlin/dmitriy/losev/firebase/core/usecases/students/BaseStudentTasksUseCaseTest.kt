package dmitriy.losev.firebase.core.usecases.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.core.TASKS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseStudentTasksUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val studentTasksReference by lazy { reference.child(STUDENTS).child(TASKS) }

    protected suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    protected suspend fun addTask(id: String) {
        studentTasksReference.child(STUDENT_ID).child(id).setValue(true).await()
    }

    protected suspend fun addTasks(count: Int) {
        repeat(count) { index ->
            addTask(id = "$TASK_ID-$index")
        }
    }

    protected suspend fun deleteTasks() {
        studentTasksReference.child(STUDENT_ID).removeValue().await()
    }

    protected suspend fun getTask(): String? {
        return getTask(key = TASK_ID)
    }

    protected suspend fun getTask(key: String): String? {
        val hasTaskInStudent = studentTasksReference.child(STUDENT_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasTaskInStudent == true) {
            studentTasksReference.child(STUDENT_ID).child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasTask(): Boolean {
        return studentTasksReference.child(STUDENT_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == TASK_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasTasks(): Boolean {
        return studentTasksReference.child(STUDENT_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val STUDENT_ID = "4324324324324234v8324324324v32"
        const val TASK_ID = "d9c4983m24382c7432m748320-432"
    }
}
package dmitriy.losev.firebase.core.usecases.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.TASKS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseGroupTasksUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val groupTasksReference by lazy { reference.child(GROUPS).child(TASKS) }

    protected suspend fun addTask() {
        addTask(id = TASK_ID)
    }

    protected suspend fun addTask(id: String) {
        groupTasksReference.child(GROUP_ID).child(id).setValue(true).await()
    }

    protected suspend fun addTasks(count: Int) {
        repeat(count) { index ->
            addTask(id = "$TASK_ID-$index")
        }
    }

    protected suspend fun deleteTasks() {
        groupTasksReference.child(GROUP_ID).removeValue().await()
    }

    protected suspend fun getTask(): String? {
        return getTask(key = TASK_ID)
    }

    protected suspend fun getTask(key: String): String? {
        val hasTaskInGroup = groupTasksReference.child(GROUP_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasTaskInGroup == true) {
            groupTasksReference.child(GROUP_ID).child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasTask(): Boolean {
        return groupTasksReference.child(GROUP_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == TASK_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasTasks(): Boolean {
        return groupTasksReference.child(GROUP_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val GROUP_ID = "4324324324324234v8324324324v32"
        const val TASK_ID = "d9c4983m24382c7432m748320-432"
    }
}
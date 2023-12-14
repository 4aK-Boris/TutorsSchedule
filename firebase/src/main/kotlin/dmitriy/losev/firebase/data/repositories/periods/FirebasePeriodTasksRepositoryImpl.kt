package dmitriy.losev.firebase.data.repositories.periods

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import kotlinx.coroutines.tasks.await

class FirebasePeriodTasksRepositoryImpl(reference: DatabaseReference): FirebasePeriodTasksRepository {

    private val periodTasks by lazy { reference.child(PERIODS).child(TASKS) }

    override suspend fun getAllTasks(periodId: String): List<String> {
        return periodTasks.child(periodId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(periodId: String, taskId: String) {
        periodTasks.child(periodId).child(taskId).setValue(true)
    }

    override suspend fun removeTask(periodId: String, taskId: String) {
        periodTasks.child(periodId).child(taskId).removeValue()
    }

    override suspend fun removeAllTasks(periodId: String) {
        periodTasks.child(periodId).removeValue()
    }
}
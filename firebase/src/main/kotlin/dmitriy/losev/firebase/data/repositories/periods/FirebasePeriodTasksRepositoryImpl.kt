package dmitriy.losev.firebase.data.repositories.periods

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.core.TASKS
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository
import kotlinx.coroutines.tasks.await

class FirebasePeriodTasksRepositoryImpl(reference: DatabaseReference): FirebasePeriodTasksRepository {

    private val periods by lazy { reference.child(PERIODS) }

    override suspend fun getAllTasks(periodId: String): List<String> {
        return periods.child(periodId).child(TASKS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addTask(periodId: String, taskId: String) {
        periods.child(periodId).child(TASKS).child(taskId).setValue(true)
    }

    override suspend fun removeTask(periodId: String, taskId: String) {
        periods.child(periodId).child(TASKS).child(taskId).removeValue()
    }

    override suspend fun removeAllTasks(periodId: String) {
        periods.child(periodId).child(TASKS).removeValue()
    }
}
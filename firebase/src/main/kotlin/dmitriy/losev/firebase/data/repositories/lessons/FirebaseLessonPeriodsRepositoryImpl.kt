package dmitriy.losev.firebase.data.repositories.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository
import kotlinx.coroutines.tasks.await

class FirebaseLessonPeriodsRepositoryImpl(reference: DatabaseReference): FirebaseLessonPeriodsRepository {

    private val periods by lazy { reference.child(LESSONS) }

    override suspend fun getAllPeriods(lessonId: String): List<String> {
        return periods.child(lessonId).child(PERIODS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addPeriod(lessonId: String, periodId: String) {
        periods.child(lessonId).child(PERIODS).child(periodId).setValue(true)
    }

    override suspend fun removePeriod(lessonId: String, periodId: String) {
        periods.child(lessonId).child(PERIODS).child(periodId).removeValue()
    }

    override suspend fun removeAllPeriods(lessonId: String) {
        periods.child(lessonId).child(PERIODS).removeValue()
    }
}
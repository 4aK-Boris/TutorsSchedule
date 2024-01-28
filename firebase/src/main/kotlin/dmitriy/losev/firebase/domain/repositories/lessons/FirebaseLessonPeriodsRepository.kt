package dmitriy.losev.firebase.domain.repositories.lessons

interface FirebaseLessonPeriodsRepository {

    suspend fun getAllPeriods(lessonId: String): List<String>

    suspend fun addPeriod(lessonId: String, periodId: String)

    suspend fun removePeriod(lessonId: String, periodId: String)

    suspend fun removeAllPeriods(lessonId: String)
}
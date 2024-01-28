package dmitriy.losev.firebase.domain.usecases.lessons.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository

class FirebaseRemoveLessonPeriodUseCase(private val firebaseLessonPeriodsRepository: FirebaseLessonPeriodsRepository) : FirebaseBaseUseCase() {

    suspend fun removePeriod(lessonId: String, periodId: String) {
        firebaseLessonPeriodsRepository.removePeriod(lessonId, periodId)
    }
}
package dmitriy.losev.firebase.domain.usecases.lessons.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository

class FirebaseAddLessonPeriodUseCase(private val firebaseLessonPeriodsRepository: FirebaseLessonPeriodsRepository) : FirebaseBaseUseCase() {

    suspend fun addPeriod(lessonId: String, periodId: String) {
        firebaseLessonPeriodsRepository.addPeriod(lessonId, periodId)
    }
}
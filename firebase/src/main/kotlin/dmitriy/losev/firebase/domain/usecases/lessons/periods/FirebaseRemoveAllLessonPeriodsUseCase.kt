package dmitriy.losev.firebase.domain.usecases.lessons.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository

class FirebaseRemoveAllLessonPeriodsUseCase(private val firebaseLessonPeriodsRepository: FirebaseLessonPeriodsRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllPeriods(lessonId: String) {
        firebaseLessonPeriodsRepository.removeAllPeriods(lessonId)
    }
}
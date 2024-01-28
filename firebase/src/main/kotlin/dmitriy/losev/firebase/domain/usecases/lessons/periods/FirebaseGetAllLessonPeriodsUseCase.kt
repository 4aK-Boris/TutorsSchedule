package dmitriy.losev.firebase.domain.usecases.lessons.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository

class FirebaseGetAllLessonPeriodsUseCase(private val firebaseLessonPeriodsRepository: FirebaseLessonPeriodsRepository) : FirebaseBaseUseCase() {

    suspend fun getAllPeriods(lessonId: String): List<String> {
        return firebaseLessonPeriodsRepository.getAllPeriods(lessonId)
    }
}
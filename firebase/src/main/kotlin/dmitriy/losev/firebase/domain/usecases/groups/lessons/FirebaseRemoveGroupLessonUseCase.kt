package dmitriy.losev.firebase.domain.usecases.groups.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository

class FirebaseRemoveGroupLessonUseCase(private val firebaseGroupLessonsRepository: FirebaseGroupLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun removeLesson(groupId: String, lessonId: String) {
        firebaseGroupLessonsRepository.removeLesson(groupId, lessonId)
    }
}
package dmitriy.losev.firebase.domain.usecases.groups.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository

class FirebaseAddGroupLessonUseCase(private val firebaseGroupLessonsRepository: FirebaseGroupLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun addLesson(groupId: String, lessonId: String) {
        firebaseGroupLessonsRepository.addLesson(groupId, lessonId)
    }
}
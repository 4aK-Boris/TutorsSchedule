package dmitriy.losev.firebase.domain.usecases.lessons.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository

class FirebaseRemoveAllLessonTasksUseCase(private val firebaseLessonTasksRepository: FirebaseLessonTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllTasks(lessonId: String) {
        firebaseLessonTasksRepository.removeAllTasks(lessonId)
    }
}
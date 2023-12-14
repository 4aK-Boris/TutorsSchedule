package dmitriy.losev.firebase.domain.usecases.lessons.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository

class FirebaseRemoveLessonTaskUseCase(private val firebaseLessonTasksRepository: FirebaseLessonTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeTask(lessonId: String, taskId: String) {
        firebaseLessonTasksRepository.removeTask(lessonId, taskId)
    }
}
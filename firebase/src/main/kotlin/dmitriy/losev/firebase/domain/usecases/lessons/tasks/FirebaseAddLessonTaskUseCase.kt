package dmitriy.losev.firebase.domain.usecases.lessons.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository

class FirebaseAddLessonTaskUseCase(private val firebaseLessonTasksRepository: FirebaseLessonTasksRepository) : FirebaseBaseUseCase() {

    suspend fun addTask(lessonId: String, taskId: String) {
        firebaseLessonTasksRepository.addTask(lessonId, taskId)
    }
}
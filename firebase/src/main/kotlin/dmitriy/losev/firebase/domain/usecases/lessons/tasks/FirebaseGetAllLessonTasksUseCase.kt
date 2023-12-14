package dmitriy.losev.firebase.domain.usecases.lessons.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonTasksRepository

class FirebaseGetAllLessonTasksUseCase(private val firebaseLessonTasksRepository: FirebaseLessonTasksRepository) : FirebaseBaseUseCase() {

    suspend fun getAllTasks(lessonId: String): List<String> {
        return firebaseLessonTasksRepository.getAllTasks(lessonId)
    }
}
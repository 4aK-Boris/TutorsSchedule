package dmitriy.losev.firebase.domain.usecases.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetTasksUseCase(
    private val firebaseTasksRepository: FirebaseTasksRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getTasks(): List<Task> {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseTasksRepository.getTasks(teacherId = user.uid)
    }
}
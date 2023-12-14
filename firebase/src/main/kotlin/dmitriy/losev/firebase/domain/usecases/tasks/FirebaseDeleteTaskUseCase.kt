package dmitriy.losev.firebase.domain.usecases.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseDeleteTaskUseCase(
    private val firebaseTasksRepository: FirebaseTasksRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteTask(taskId: String) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseTasksRepository.deleteTask(teacherId = user.uid, taskId = taskId)
    }
}
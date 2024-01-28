package dmitriy.losev.firebase.domain.usecases.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseUpdateTaskUseCase(
    private val firebaseTasksRepository: FirebaseTasksRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun updateTask(taskId: String, task: Task) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseTasksRepository.updateTask(teacherId = user.uid, taskId = taskId, task = task)
    }
}
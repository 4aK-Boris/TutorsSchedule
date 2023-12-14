package dmitriy.losev.firebase.domain.usecases.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableTaskException
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetTaskUseCase(
    private val firebaseTasksRepository: FirebaseTasksRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getTask(taskId: String): Task {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseTasksRepository.getTask(teacherId = user.uid, taskId = taskId) ?: throw NullableTaskException()
    }
}
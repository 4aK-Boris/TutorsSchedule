package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseRemoveGroupTaskUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository
) : FirebaseBaseUseCase() {

    suspend fun removeTask(groupId: String, taskId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseGroupTasksRepository.removeTask(teacherId, groupId, taskId)
    }
}
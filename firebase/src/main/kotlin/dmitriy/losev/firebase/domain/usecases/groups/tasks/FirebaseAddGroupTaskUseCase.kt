package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseAddGroupTaskUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository
) : FirebaseBaseUseCase() {

    suspend fun addTask(groupId: String, taskId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseGroupTasksRepository.addTask(teacherId, groupId, taskId)
    }
}
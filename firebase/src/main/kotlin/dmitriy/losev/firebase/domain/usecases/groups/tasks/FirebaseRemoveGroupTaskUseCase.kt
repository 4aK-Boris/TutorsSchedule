package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository

class FirebaseRemoveGroupTaskUseCase(private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeTask(groupId: String, taskId: String) {
        firebaseGroupTasksRepository.removeTask(groupId, taskId)
    }
}
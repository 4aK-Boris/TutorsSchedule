package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository

class FirebaseAddGroupTaskUseCase(private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository) : FirebaseBaseUseCase() {

    suspend fun addTask(groupId: String, taskId: String) {
        firebaseGroupTasksRepository.addTask(groupId, taskId)
    }
}
package dmitriy.losev.firebase.domain.usecases.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.TaskAddException
import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.repositories.tasks.FirebaseTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseAddTaskUseCase(
    private val firebaseTasksRepository: FirebaseTasksRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun addTask(task: Task): String {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseTasksRepository.addTask(teacherId = user.uid, task = task) ?: throw TaskAddException()
    }
}
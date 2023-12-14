package dmitriy.losev.firebase.domain.usecases.students.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository

class FirebaseRemoveStudentTaskUseCase(private val firebaseStudentTasksRepository: FirebaseStudentTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeTask(studentId: String, taskId: String) {
        firebaseStudentTasksRepository.removeTask(studentId, taskId)
    }
}
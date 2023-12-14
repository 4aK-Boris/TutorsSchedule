package dmitriy.losev.firebase.domain.usecases.students.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository

class FirebaseAddStudentTaskUseCase(private val firebaseStudentTasksRepository: FirebaseStudentTasksRepository) : FirebaseBaseUseCase() {

    suspend fun addTask(studentId: String, taskId: String) {
        firebaseStudentTasksRepository.addTask(studentId, taskId)
    }
}
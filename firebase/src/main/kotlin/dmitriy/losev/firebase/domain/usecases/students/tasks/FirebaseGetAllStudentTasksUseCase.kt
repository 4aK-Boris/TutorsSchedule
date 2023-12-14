package dmitriy.losev.firebase.domain.usecases.students.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository

class FirebaseGetAllStudentTasksUseCase(private val firebaseStudentTasksRepository: FirebaseStudentTasksRepository) : FirebaseBaseUseCase() {

    suspend fun getAllTasks(studentId: String): List<String> {
        return firebaseStudentTasksRepository.getAllTasks(studentId)
    }
}
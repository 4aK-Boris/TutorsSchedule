package dmitriy.losev.firebase.domain.usecases.students.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository

class FirebaseRemoveAllStudentTasksUseCase(private val firebaseStudentTasksRepository: FirebaseStudentTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllTasks(studentId: String) {
        firebaseStudentTasksRepository.removeAllTasks(studentId)
    }
}
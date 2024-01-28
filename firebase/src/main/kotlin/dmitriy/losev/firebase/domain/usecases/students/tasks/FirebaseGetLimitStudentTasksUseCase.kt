package dmitriy.losev.firebase.domain.usecases.students.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository

class FirebaseGetLimitStudentTasksUseCase(private val firebaseStudentTasksRepository: FirebaseStudentTasksRepository) : FirebaseBaseUseCase() {

    suspend fun getLimitTasks(studentId: String, count: Int): List<String> {
        return firebaseStudentTasksRepository.getLimitTasks(studentId, count)
    }
}
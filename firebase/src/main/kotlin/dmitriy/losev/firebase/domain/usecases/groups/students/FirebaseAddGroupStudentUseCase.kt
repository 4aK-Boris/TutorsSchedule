package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository

class FirebaseAddGroupStudentUseCase(private val firebaseGroupStudentsRepository: FirebaseGroupStudentsRepository) : FirebaseBaseUseCase() {

    suspend fun addStudent(groupId: String, studentId: String) {
        firebaseGroupStudentsRepository.addStudent(groupId, studentId)
    }
}
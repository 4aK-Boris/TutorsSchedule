package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository

class FirebaseRemoveGroupStudentUseCase(private val firebaseGroupStudentsRepository: FirebaseGroupStudentsRepository) : FirebaseBaseUseCase() {

    suspend fun removeStudent(groupId: String, studentId: String) {
        firebaseGroupStudentsRepository.removeStudent(groupId, studentId)
    }
}
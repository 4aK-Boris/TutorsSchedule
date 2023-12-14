package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository

class FirebaseRemoveAllStudentGroupsUseCase(private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllGroups(studentId: String) {
        firebaseStudentGroupsRepository.removeAllGroups(studentId)
    }
}
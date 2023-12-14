package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository

class FirebaseAddStudentGroupUseCase(private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository) : FirebaseBaseUseCase() {

    suspend fun addGroup(studentId: String, groupId: String) {
        firebaseStudentGroupsRepository.addGroup(studentId, groupId)
    }
}
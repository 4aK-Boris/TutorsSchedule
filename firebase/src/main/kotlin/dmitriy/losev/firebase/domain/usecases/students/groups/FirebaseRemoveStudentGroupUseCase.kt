package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository

class FirebaseRemoveStudentGroupUseCase(private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository) : FirebaseBaseUseCase() {

    suspend fun removeGroup(studentId: String, groupId: String) {
        firebaseStudentGroupsRepository.removeGroup(studentId, groupId)
    }
}
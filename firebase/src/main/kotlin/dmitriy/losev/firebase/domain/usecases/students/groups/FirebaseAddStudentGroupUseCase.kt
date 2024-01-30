package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseAddStudentGroupUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository
) : FirebaseBaseUseCase() {

    suspend fun addGroup(studentId: String, groupId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseStudentGroupsRepository.addGroup(teacherId, studentId, groupId)
    }
}
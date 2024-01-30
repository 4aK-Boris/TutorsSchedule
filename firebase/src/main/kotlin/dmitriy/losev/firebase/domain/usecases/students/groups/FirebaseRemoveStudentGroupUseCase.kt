package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseRemoveStudentGroupUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository
) : FirebaseBaseUseCase() {

    suspend fun removeGroup(studentId: String, groupId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseStudentGroupsRepository.removeGroup(teacherId, studentId, groupId)
    }
}
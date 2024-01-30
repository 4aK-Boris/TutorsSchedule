package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetStudentGroupIdsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository
) : FirebaseBaseUseCase() {

    suspend fun getGroupIds(studentId: String): List<String> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseStudentGroupsRepository.getGroups(teacherId, studentId)
    }
}
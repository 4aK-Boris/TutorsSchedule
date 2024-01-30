package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetLimitStudentGroupsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository
) : FirebaseBaseUseCase() {

    suspend fun getLimitGroups(studentId: String, count: Int): List<String> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseStudentGroupsRepository.getLimitGroups(teacherId, studentId, count)
    }
}
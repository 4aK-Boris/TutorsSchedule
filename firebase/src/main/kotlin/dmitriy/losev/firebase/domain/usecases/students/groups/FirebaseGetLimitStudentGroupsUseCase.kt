package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository

class FirebaseGetLimitStudentGroupsUseCase(private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository) : FirebaseBaseUseCase() {

    suspend fun getLimitGroups(studentId: String, count: Int): List<String> {
        return firebaseStudentGroupsRepository.getLimitGroups(studentId, count)
    }
}
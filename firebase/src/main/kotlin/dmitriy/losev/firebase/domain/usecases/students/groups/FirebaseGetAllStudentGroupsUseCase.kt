package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository

class FirebaseGetAllStudentGroupsUseCase(private val firebaseStudentGroupsRepository: FirebaseStudentGroupsRepository) : FirebaseBaseUseCase() {

    suspend fun getAllGroups(studentId: String): List<String> {
        return firebaseStudentGroupsRepository.getAllGroups(studentId)
    }
}
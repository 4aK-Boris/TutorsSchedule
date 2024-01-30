package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseRemoveGroupStudentUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupStudentsRepository: FirebaseGroupStudentsRepository
) : FirebaseBaseUseCase() {

    suspend fun removeStudent(groupId: String, studentId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseGroupStudentsRepository.removeStudent(teacherId, groupId, studentId)
    }
}
package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetGroupStudentIdsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupStudentsRepository: FirebaseGroupStudentsRepository
) : FirebaseBaseUseCase() {

    suspend fun getGroupStudentIds(groupId: String): List<String> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseGroupStudentsRepository.getStudents(teacherId, groupId)
    }
}
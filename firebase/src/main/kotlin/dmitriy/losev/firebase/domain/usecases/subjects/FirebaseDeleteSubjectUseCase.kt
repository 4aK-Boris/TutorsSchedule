package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseDeleteSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteSubject(subjectId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseSubjectsRepository.deleteSubject(teacherId, subjectId)
    }
}
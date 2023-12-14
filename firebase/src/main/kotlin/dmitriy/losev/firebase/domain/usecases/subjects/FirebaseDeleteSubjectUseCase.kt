package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseDeleteSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteSubject(subjectId: String) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseSubjectsRepository.deleteSubject(teacherId = user.uid, subjectId = subjectId)
    }
}
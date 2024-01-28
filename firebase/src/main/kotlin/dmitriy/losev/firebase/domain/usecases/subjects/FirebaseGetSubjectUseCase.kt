package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableSubjectException
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun getSubject(subjectId: String): Subject {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseSubjectsRepository.getSubject(teacherId, subjectId) ?: throw NullableSubjectException()
    }
}
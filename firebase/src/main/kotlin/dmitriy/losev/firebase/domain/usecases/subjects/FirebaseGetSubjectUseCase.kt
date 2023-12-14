package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableSubjectException
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getSubject(subjectId: String): Subject {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseSubjectsRepository.getSubject(teacherId = user.uid, subjectId = subjectId) ?: throw NullableSubjectException()
    }
}
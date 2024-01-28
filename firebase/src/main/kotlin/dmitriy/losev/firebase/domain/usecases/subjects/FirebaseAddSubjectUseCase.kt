package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.SubjectAddException
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseAddSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun addSubject(subject: Subject): String {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseSubjectsRepository.addSubject(teacherId, subject) ?: throw SubjectAddException()
    }
}
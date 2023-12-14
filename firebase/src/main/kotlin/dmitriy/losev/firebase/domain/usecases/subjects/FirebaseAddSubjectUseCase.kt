package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.SubjectAddException
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseAddSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun addSubject(subject: Subject): String {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseSubjectsRepository.addSubject(teacherId = user.uid, subject = subject) ?: throw SubjectAddException()
    }
}
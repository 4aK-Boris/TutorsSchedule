package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseUpdateSubjectUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun updateSubject(subjectId: String, subject: Subject) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseSubjectsRepository.updateSubject(teacherId = user.uid, subjectId = subjectId, subject = subject)
    }
}
package dmitriy.losev.firebase.domain.usecases.subjects

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetSubjectsUseCase(
    private val firebaseSubjectsRepository: FirebaseSubjectsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getSubjects(): List<Subject> {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseSubjectsRepository.getSubjects(teacherId = user.uid)
    }
}
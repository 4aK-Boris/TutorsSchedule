package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseDeleteContactsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseContactsRepository: FirebaseContactsRepository
) : FirebaseBaseUseCase() {

    suspend fun deleteContacts(studentId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseContactsRepository.deleteContacts(teacherId, studentId)
    }
}
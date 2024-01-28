package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseDeleteContactUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseContactsRepository: FirebaseContactsRepository
) : FirebaseBaseUseCase() {

    suspend fun deleteContact(studentId: String, contactId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseContactsRepository.deleteContact(teacherId, studentId, contactId)
    }
}
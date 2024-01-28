package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseUpdateContactUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseContactsRepository: FirebaseContactsRepository
): FirebaseBaseUseCase() {

    suspend fun updateContact(studentId: String, contactId: String, contact: Contact) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseContactsRepository.updateContact(teacherId, studentId, contactId, contact)
    }
}
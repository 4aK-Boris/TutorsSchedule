package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository

class FirebaseDeleteContactUseCase(private val firebaseContactsRepository: FirebaseContactsRepository) : FirebaseBaseUseCase() {

    suspend fun deleteContact(studentId: String, contactId: String) {
        firebaseContactsRepository.deleteContact(studentId, contactId)
    }
}
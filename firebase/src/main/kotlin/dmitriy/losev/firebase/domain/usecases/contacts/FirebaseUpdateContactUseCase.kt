package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository

class FirebaseUpdateContactUseCase(private val firebaseContactsRepository: FirebaseContactsRepository): FirebaseBaseUseCase() {

    suspend fun updateContact(studentId: String, contactId: String, contact: Contact) {
        firebaseContactsRepository.updateContact(studentId, contactId, contact)
    }
}
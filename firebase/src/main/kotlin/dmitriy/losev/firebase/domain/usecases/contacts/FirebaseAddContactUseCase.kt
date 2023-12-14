package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository

class FirebaseAddContactUseCase(private val firebaseContactsRepository: FirebaseContactsRepository): FirebaseBaseUseCase() {

    suspend fun addContact(studentId: String, contact: Contact): String? {
        return firebaseContactsRepository.addContact(studentId, contact)
    }
}
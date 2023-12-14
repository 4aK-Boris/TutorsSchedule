package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository

class FirebaseGetContactsUseCase(private val firebaseContactsRepository: FirebaseContactsRepository): FirebaseBaseUseCase() {

    suspend fun getContacts(studentId: String): List<Contact> {
        return firebaseContactsRepository.getContacts(studentId)
    }
}
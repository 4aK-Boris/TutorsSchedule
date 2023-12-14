package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableContactException
import dmitriy.losev.firebase.domain.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository

class FirebaseGetContactUseCase(private val firebaseContactsRepository: FirebaseContactsRepository): FirebaseBaseUseCase() {

    suspend fun getContact(studentId: String, contactId: String): Contact {
        return firebaseContactsRepository.getContact(studentId, contactId) ?: throw NullableContactException()
    }
}
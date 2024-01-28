package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.ContactAddException
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseAddContactUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseContactsRepository: FirebaseContactsRepository
): FirebaseBaseUseCase() {

    suspend fun addContact(studentId: String, contact: Contact): String {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseContactsRepository.addContact(teacherId, studentId, contact) ?: throw ContactAddException()
    }
}
package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableContactException
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetContactUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseContactsRepository: FirebaseContactsRepository
): FirebaseBaseUseCase() {

    suspend fun getContact(studentId: String, contactId: String): Contact {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseContactsRepository.getContact(teacherId, studentId, contactId) ?: throw NullableContactException()
    }
}
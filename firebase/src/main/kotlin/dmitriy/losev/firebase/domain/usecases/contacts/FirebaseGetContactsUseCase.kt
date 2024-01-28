package dmitriy.losev.firebase.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetContactsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseContactsRepository: FirebaseContactsRepository
): FirebaseBaseUseCase() {

    suspend fun getContacts(studentId: String): List<Contact> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseContactsRepository.getContacts(teacherId, studentId)
    }
}
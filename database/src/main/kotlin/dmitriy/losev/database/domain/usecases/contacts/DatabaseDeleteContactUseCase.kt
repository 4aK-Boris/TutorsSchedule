package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.ContactsRepository

class DatabaseDeleteContactUseCase(private val contactsRepository: ContactsRepository): DatabaseBaseUseCase() {

    suspend fun deleteContact(studentId: String, contactId: String) {
        contactsRepository.deleteContact(studentId, contactId)
    }
}
package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.ContactsRepository

class DatabaseUpdateContactUseCase(private val contactsRepository: ContactsRepository): DatabaseBaseUseCase() {

    suspend fun updateContact(studentId: String, contact: Contact) {
        contactsRepository.updateContact(studentId, contact)
    }
}
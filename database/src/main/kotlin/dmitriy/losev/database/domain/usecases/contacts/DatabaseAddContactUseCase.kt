package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.ContactsRepository

class DatabaseAddContactUseCase(private val contactsRepository: ContactsRepository): DatabaseBaseUseCase() {

    suspend fun addContact(studentId: String, contact: Contact) {
        contactsRepository.addContact(studentId, contact)
    }
}
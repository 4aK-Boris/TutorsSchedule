package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.ContactsRepository

class DatabaseDeleteContactsUseCase(private val contactsRepository: ContactsRepository): DatabaseBaseUseCase() {

    suspend fun deleteContacts(studentId: String, contacts: List<Contact>) {
        contactsRepository.deleteContacts(studentId, contacts)
    }
}
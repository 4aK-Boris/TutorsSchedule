package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.ContactsRepository

class DatabaseGetContactsUseCase(private val contactsRepository: ContactsRepository): DatabaseBaseUseCase() {

    suspend fun getContacts(studentId: String): List<Contact> {
        return contactsRepository.getContacts(studentId)
    }
}
package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.ContactsRepository

class DatabaseGetContactUseCase(private val contactsRepository: ContactsRepository): DatabaseBaseUseCase() {

    suspend fun getContact(studentId: String, contactId: String): Contact? {
        return contactsRepository.getContact(studentId, contactId)
    }
}
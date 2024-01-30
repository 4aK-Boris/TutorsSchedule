package dmitriy.losev.database.domain.repositories

import dmitriy.losev.core.models.Contact

interface ContactsRepository {

    suspend fun addContact(studentId: String, contact: Contact)

    suspend fun updateContact(studentId: String, contact: Contact)

    suspend fun saveContact(studentId: String, contact: Contact)

    suspend fun deleteContact(studentId: String, contactId: String)

    suspend fun deleteContacts(studentId: String, contacts: List<Contact>)

    suspend fun getContact(studentId: String, contactId: String): Contact?
}
package dmitriy.losev.database.domain.repositories

import dmitriy.losev.core.models.Contact

interface ContactsRepository {

    suspend fun addContact(studentId: String, contact: Contact)

    suspend fun updateContact(studentId: String, contact: Contact)

    suspend fun saveContact(studentId: String, contact: Contact)

    suspend fun saveContacts(studentId: String, contacts: List<Contact>)

    suspend fun deleteContact(studentId: String, contactId: String)

    suspend fun deleteContacts(studentId: String)

    suspend fun getContact(studentId: String, contactId: String): Contact?

    suspend fun getContacts(studentId: String): List<Contact>
}
package dmitriy.losev.firebase.domain.repositories

import dmitriy.losev.firebase.domain.models.Contact

interface FirebaseContactsRepository {

    suspend fun addContact(studentId: String, contact: Contact): String?

    suspend fun updateContact(studentId: String, contactId: String, contact: Contact)

    suspend fun deleteContact(studentId: String, contactId: String)

    suspend fun getContact(studentId: String, contactId: String): Contact?

    suspend fun getContacts(studentId: String): List<Contact>
}
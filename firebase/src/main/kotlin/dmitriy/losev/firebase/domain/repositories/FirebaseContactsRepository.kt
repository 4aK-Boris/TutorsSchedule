package dmitriy.losev.firebase.domain.repositories

import dmitriy.losev.core.models.Contact

interface FirebaseContactsRepository {

    suspend fun addContact(teacherId: String, studentId: String, contact: Contact): String?

    suspend fun updateContact(teacherId: String, studentId: String, contactId: String, contact: Contact)

    suspend fun deleteContact(teacherId: String, studentId: String, contactId: String)

    suspend fun deleteContacts(teacherId: String, studentId: String)

    suspend fun getContact(teacherId: String, studentId: String, contactId: String): Contact?

    suspend fun getContacts(teacherId: String, studentId: String): List<Contact>
}
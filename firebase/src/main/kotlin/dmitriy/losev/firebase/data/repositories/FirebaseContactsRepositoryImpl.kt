package dmitriy.losev.firebase.data.repositories

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.core.CONTACTS
import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import kotlinx.coroutines.tasks.await

class FirebaseContactsRepositoryImpl(
    private val reference: DatabaseReference,
    private val contactMapper: ContactMapper
) : FirebaseContactsRepository {

    override suspend fun getContact(teacherId: String, studentId: String, contactId: String): Contact? {
        return getContactsReference(teacherId, studentId).child(contactId).get().await().getValue(ContactDTO::class.java)?.let { contactDTO ->
            contactMapper.map(value = contactDTO)
        }
    }

    override suspend fun addContact(teacherId: String, studentId: String, contact: Contact): String? {
        val contactDTO = contactMapper.map(value = contact)
        getContactsReference(teacherId, studentId).push().apply {
            setValue(contactDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun updateContact(teacherId: String, studentId: String, contactId: String, contact: Contact) {
        getContactsReference(teacherId, studentId).updateChildren(mapOf(contactId to contactMapper.map(value = contact))).await()
    }

    override suspend fun deleteContact(teacherId: String, studentId: String, contactId: String) {
        getContactsReference(teacherId, studentId).child(contactId).removeValue().await()
    }

    override suspend fun deleteContacts(teacherId: String, studentId: String) {
        getContactsReference(teacherId, studentId).removeValue().await()
    }

    override suspend fun getContacts(teacherId: String, studentId: String): List<Contact> {
        return getContactsReference(teacherId, studentId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(ContactDTO::class.java)?.let { contactMapper.map(value = it) }
        }
    }

    private fun getContactsReference(teacherId: String, studentId: String): DatabaseReference {
        return reference.child(teacherId).child(CONTACTS).child(studentId)
    }
}
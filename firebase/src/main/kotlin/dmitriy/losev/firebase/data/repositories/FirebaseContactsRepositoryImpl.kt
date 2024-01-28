package dmitriy.losev.firebase.data.repositories

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.CONTACTS
import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.firebase.domain.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import kotlinx.coroutines.tasks.await

class FirebaseContactsRepositoryImpl(
    reference: DatabaseReference,
    private val contactMapper: ContactMapper
) : FirebaseContactsRepository {

    private val contacts by lazy { reference.child(CONTACTS) }

    override suspend fun getContact(studentId: String, contactId: String): Contact? {
        return contacts.child(studentId).child(contactId).get().await().getValue(ContactDTO::class.java)?.let { contactDTO ->
            contactMapper.map(value = contactDTO)
        }
    }

    override suspend fun addContact(studentId: String, contact: Contact): String? {
        val contactDTO = contactMapper.map(value = contact)
        contacts.child(studentId).push().apply {
            setValue(contactDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun updateContact(studentId: String, contactId: String, contact: Contact) {
        contacts.child(studentId).updateChildren(mapOf(contactId to contactMapper.map(value = contact))).await()
    }

    override suspend fun deleteContact(studentId: String, contactId: String) {
        contacts.child(studentId).child(contactId).removeValue().await()
    }

    override suspend fun getContacts(studentId: String): List<Contact> {
        return contacts.child(studentId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(ContactDTO::class.java)?.let { contactMapper.map(value = it) }
        }
    }
}
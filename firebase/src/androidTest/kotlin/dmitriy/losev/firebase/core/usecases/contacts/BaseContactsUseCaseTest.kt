package dmitriy.losev.firebase.core.usecases.contacts

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.CONTACTS
import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.core.models.Contact
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseContactsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val contactMapper by inject<ContactMapper>()

    private val contactsReference by lazy { reference.child(CONTACTS) }

    protected suspend fun addContact() {
        addContact(id = CONTACT_ID)
    }

    protected suspend fun addContact(id: String) {
        contactsReference.child(STUDENT_ID).child(id).setValue(contactMapper.map(value = contact.copy(id = id))).await()
    }

    protected suspend fun deleteContacts() {
        contactsReference.child(STUDENT_ID).get().await().children.forEach { contact ->
            contact.ref.removeValue().await()
        }
    }

    protected suspend fun getContact(): Contact? {
        return getContact(key = CONTACT_ID)
    }

    protected suspend fun getContact(key: String): Contact? {
        return contactsReference.child(STUDENT_ID).child(key).get().await().getValue(ContactDTO::class.java)?.let { contactDTO ->
            contactMapper.map(value = contactDTO)
        }
    }

    protected suspend fun hasContact(): Boolean {
        return contactsReference.child(STUDENT_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val NAME = "Юрий Степанович"
        const val PHONE_NUMBER = "+79037805695"

        const val STUDENT_ID = "702yn476f32478n632584c320496732c42"
        const val CONTACT_ID = "c476732784637294637286482364v59763"

        val contact = Contact(
            id = CONTACT_ID,
            name = NAME,
            phoneNumber = PHONE_NUMBER
        )
    }
}
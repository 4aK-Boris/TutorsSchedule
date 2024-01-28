package dmitriy.losev.firebase.repositories

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.CONTACTS
import dmitriy.losev.firebase.core.usecases.contacts.BaseContactsUseCaseTest
import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseContactsRepositoryTest: BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val contactMapper by inject<ContactMapper>()

    private val contactsReference by lazy { reference.child(CONTACTS) }

    private val firebaseContactsRepository by inject<FirebaseContactsRepository>()

    override suspend fun tearDown() {
        deleteContacts()
    }

    @Test
    fun testAddContact(): Unit = runBlocking {

        val key = firebaseContactsRepository.addContact(STUDENT_ID, contact)!!

        val hasContact = hasContact()

        assertTrue(hasContact)

        val actualResult = getContact(key)

        assertEquals(contact.copy(id = key), actualResult)
    }

    @Test
    fun testUpdateContact(): Unit = runBlocking {

        addContact()

        val hasContact = hasContact()

        assertTrue(hasContact)

        val newContact = contact.copy(name = NEW_NAME, phoneNumber = NEW_PHONE_NUMBER)

        firebaseContactsRepository.updateContact(STUDENT_ID, CONTACT_ID, newContact)

        val actualResult = getContact()

        assertEquals(newContact, actualResult)
    }

    @Test
    fun testDeleteStudent(): Unit = runBlocking {

        addContact()

        var hasContact = hasContact()

        assertTrue(hasContact)

        firebaseContactsRepository.deleteContact(STUDENT_ID, CONTACT_ID)

        hasContact = hasContact()

        assertFalse(hasContact)
    }

    @Test
    fun testGetStudent(): Unit = runBlocking {

        addContact()

        val hasContact = hasContact()

        assertTrue(hasContact)

        val actualResult = firebaseContactsRepository.getContact(STUDENT_ID, CONTACT_ID)

        assertEquals(BaseContactsUseCaseTest.contact, actualResult)
    }

    @Test
    fun testGetContacts(): Unit = runBlocking {

        val size = 3

        repeat(size) { index ->
            addContact(id = "${CONTACT_ID}$index")
        }

        val actualResult = firebaseContactsRepository.getContacts(STUDENT_ID)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, contact ->
            assertEquals("${CONTACT_ID}$index", contact.id)
            assertEquals(NAME, contact.name)
            assertEquals(PHONE_NUMBER, contact.phoneNumber)
        }
    }


    private suspend fun addContact() {
        addContact(id = CONTACT_ID)
    }

    private suspend fun addContact(id: String) {
        contactsReference.child(STUDENT_ID).child(id).setValue(contactMapper.map(value = contact.copy(id = id))).await()
    }

    private suspend fun deleteContacts() {
        contactsReference.child(STUDENT_ID).get().await().children.forEach { contact ->
            contact.ref.removeValue().await()
        }
    }

    private suspend fun getContact(): Contact? {
        return getContact(key = CONTACT_ID)
    }

    private suspend fun getContact(key: String): Contact? {
        return contactsReference.child(STUDENT_ID).child(key).get().await().getValue(ContactDTO::class.java)?.let { contactDTO ->
            contactMapper.map(value = contactDTO)
        }
    }

    private suspend fun hasContact(): Boolean {
        return contactsReference.child(STUDENT_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val NAME = "Юрий Степанович"
        private const val PHONE_NUMBER = "+79037805695"

        private const val NEW_NAME = "Ирина Витальевна"
        private const val NEW_PHONE_NUMBER = "+79037857858"

        private const val STUDENT_ID = "702yn476f32478n632584c320496732c42"
        private const val CONTACT_ID = "c476732784637294637286482364v59763"

        private val contact = Contact(
            id = CONTACT_ID,
            name = NAME,
            phoneNumber = PHONE_NUMBER
        )
    }
}
package dmitriy.losev.firebase.usecases.contacts

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.contacts.BaseContactsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseUpdateContactUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseUpdateContactUseCaseTest : BaseContactsUseCaseTest() {

    private val firebaseUpdateContactUseCase by inject<FirebaseUpdateContactUseCase>()

    override suspend fun setUp() {
        logIn()
        addContact()
    }

    override suspend fun tearDown() {
        deleteContacts()
        logOut()
    }

    @Test
    fun testUpdateContact(): Unit = runBlocking {

        val hasContact = hasContact()

        assertTrue(hasContact)

        val newContact = contact.copy(name = NAME, phoneNumber = PHONE_NUMBER)

        firebaseUpdateContactUseCase.updateContact(STUDENT_ID, CONTACT_ID, newContact)

        val actualResult = getContact()

        assertEquals(newContact, actualResult)
    }

    companion object {
        private const val NAME = "Ирина Витальевна"
        private const val PHONE_NUMBER = "+79037857858"
    }
}
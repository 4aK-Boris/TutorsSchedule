package dmitriy.losev.firebase.usecases.contacts

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.contacts.BaseContactsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseAddContactUseCase
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseAddContactUseCaseTest : BaseContactsUseCaseTest() {

    private val firebaseAddContactUseCase by inject<FirebaseAddContactUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteContacts()
        logOut()
    }

    @Test
    fun testAddContact(): Unit = runBlocking {

        val key = firebaseAddContactUseCase.addContact(STUDENT_ID, contact)!!

        val hasContact = hasContact()

        assertTrue(hasContact)

        val actualResult = getContact(key)

        assertEquals(contact.copy(id = key), actualResult)
    }
}
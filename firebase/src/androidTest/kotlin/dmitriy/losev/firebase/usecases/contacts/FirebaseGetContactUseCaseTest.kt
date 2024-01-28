package dmitriy.losev.firebase.usecases.contacts

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.contacts.BaseContactsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseGetContactUseCaseTest : BaseContactsUseCaseTest() {

    private val firebaseGetContactUseCase by inject<FirebaseGetContactUseCase>()

    override suspend fun setUp() {
        logIn()
        addContact()
    }

    override suspend fun tearDown() {
        deleteContacts()
        logOut()
    }

    @Test
    fun testGetStudent(): Unit = runBlocking {

        val hasContact = hasContact()

        assertTrue(hasContact)

        val actualResult = firebaseGetContactUseCase.getContact(STUDENT_ID, CONTACT_ID)

        assertEquals(contact, actualResult)
    }
}
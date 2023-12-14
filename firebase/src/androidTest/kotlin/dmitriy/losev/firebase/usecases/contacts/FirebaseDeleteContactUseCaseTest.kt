package dmitriy.losev.firebase.usecases.contacts

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.contacts.BaseContactsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseDeleteContactUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseDeleteContactUseCaseTest : BaseContactsUseCaseTest() {

    private val firebaseDeleteContactUseCase by inject<FirebaseDeleteContactUseCase>()

    override suspend fun setUp() {
        addContact()
        logIn()
    }

    override suspend fun tearDown() {
        deleteContacts()
        logOut()
    }

    @Test
    fun testDeleteStudent(): Unit = runBlocking {

        var hasContact = hasContact()

        assertTrue(hasContact)

        firebaseDeleteContactUseCase.deleteContact(STUDENT_ID, CONTACT_ID)

        hasContact = hasContact()

        assertFalse(hasContact)
    }
}
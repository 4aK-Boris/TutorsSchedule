package dmitriy.losev.firebase.usecases.contacts

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.contacts.BaseContactsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseGetContactsUseCaseTest: BaseContactsUseCaseTest() {

    private val firebaseGetContactsUseCase by inject<FirebaseGetContactsUseCase>()

    override suspend fun setUp() {
        logIn()
        addContact(ID_1)
        addContact(ID_2)
        addContact(ID_3)
    }

    override suspend fun tearDown() {
        deleteContacts()
        logOut()
    }

    @Test
    fun testGetContacts(): Unit = runBlocking {

        val actualResult = firebaseGetContactsUseCase.getContacts(STUDENT_ID)

        assertEquals(SIZE, actualResult.size)

        actualResult.forEachIndexed { index, contact ->
            assertEquals("id_${index + 1}", contact.id)
            assertEquals(NAME, contact.name)
            assertEquals(PHONE_NUMBER, contact.phoneNumber)
        }
    }

    companion object {
        private const val SIZE = 3

        private const val ID_1 = "id_1"
        private const val ID_2 = "id_2"
        private const val ID_3 = "id_3"
    }
}
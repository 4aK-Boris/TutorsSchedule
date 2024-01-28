package dmitriy.losev.students.domain.usecases.contact

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.domain.usecases.contacts.DatabaseGetContactsUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseSaveContactsUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactsUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetContactsUseCase(
    private val firebaseGetContactsUseCase: FirebaseGetContactsUseCase,
    private val databaseGetContactsUseCase: DatabaseGetContactsUseCase,
    private val databaseSaveContactsUseCase: DatabaseSaveContactsUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getContacts(studentId: String, onFirebaseLoading: (List<Contact>) -> Unit, onDatabaseLoading: (List<Contact>) -> Unit) {
        loadAllData(
            loadFromFirebase = { firebaseGetContactsUseCase.getContacts(studentId) },
            loadFromDatabase = { databaseGetContactsUseCase.getContacts(studentId) },
            saveToDatabase = { contacts -> databaseSaveContactsUseCase.saveContacts(studentId, contacts) },
            onFirebaseLoading = onFirebaseLoading,
            onDatabaseLoading = onDatabaseLoading
        )
    }

    suspend fun getContacts(studentId: String, onLoading: (List<Contact>) -> Unit) {
        loadAllData(
            loadFromFirebase = { firebaseGetContactsUseCase.getContacts(studentId) },
            loadFromDatabase = { databaseGetContactsUseCase.getContacts(studentId) },
            saveToDatabase = { contacts -> databaseSaveContactsUseCase.saveContacts(studentId, contacts) },
            onFirebaseLoading = onLoading,
            onDatabaseLoading = onLoading
        )
    }
}
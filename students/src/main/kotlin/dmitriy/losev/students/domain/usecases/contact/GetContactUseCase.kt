package dmitriy.losev.students.domain.usecases.contact

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.domain.usecases.contacts.DatabaseGetContactUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseSaveContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetContactUseCase(
    private val firebaseGetContactUseCase: FirebaseGetContactUseCase,
    private val databaseGetContactUseCase: DatabaseGetContactUseCase,
    private val databaseSaveContactUseCase: DatabaseSaveContactUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getContact(studentId: String, contactId: String, onFirebaseLoading: (Contact) -> Unit, onDatabaseLoading: (Contact?) -> Unit): Unit = loadData(
        loadFromFirebase = { firebaseGetContactUseCase.getContact(studentId, contactId) },
        loadFromDatabase = { databaseGetContactUseCase.getContact(studentId, contactId) },
        saveToDatabase = { contact -> databaseSaveContactUseCase.saveContact(studentId, contact) },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )
}
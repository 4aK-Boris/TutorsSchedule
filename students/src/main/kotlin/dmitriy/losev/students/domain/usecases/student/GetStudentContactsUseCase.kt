package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.domain.usecases.contacts.DatabaseDeleteContactsUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseSaveContactsUseCase
import dmitriy.losev.database.domain.usecases.students.contacts.DatabaseGetStudentContactsUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactsUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetStudentContactsUseCase(
    private val firebaseGetContactsUseCase: FirebaseGetContactsUseCase,
    private val databaseGetStudentContactsUseCase: DatabaseGetStudentContactsUseCase,
    private val databaseSaveContactsUseCase: DatabaseSaveContactsUseCase,
    private val databaseDeleteContactsUseCase: DatabaseDeleteContactsUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getStudentContacts(studentId: String, onFirebaseLoading: (List<Contact>) -> Unit, onDatabaseLoading: (List<Contact>) -> Unit) {
        loadAllData(
            loadFromFirebase = { firebaseGetContactsUseCase.getContacts(studentId) },
            loadFromDatabase = { databaseGetStudentContactsUseCase.getStudentContacts(studentId) },
            saveToDatabase = { contacts -> databaseSaveContactsUseCase.saveContacts(studentId, contacts) },
            deleteFromDatabase = { contacts -> databaseDeleteContactsUseCase.deleteContacts(studentId, contacts) },
            onFirebaseLoading = onFirebaseLoading,
            onDatabaseLoading = onDatabaseLoading
        )
    }

    suspend fun getStudentContacts(studentId: String, onLoading: (List<Contact>) -> Unit) {
        loadAllData(
            loadFromFirebase = { firebaseGetContactsUseCase.getContacts(studentId) },
            loadFromDatabase = { databaseGetStudentContactsUseCase.getStudentContacts(studentId) },
            saveToDatabase = { contacts -> databaseSaveContactsUseCase.saveContacts(studentId, contacts) },
            deleteFromDatabase = { contacts -> databaseDeleteContactsUseCase.deleteContacts(studentId, contacts) },
            onFirebaseLoading = onLoading,
            onDatabaseLoading = onLoading
        )
    }
}
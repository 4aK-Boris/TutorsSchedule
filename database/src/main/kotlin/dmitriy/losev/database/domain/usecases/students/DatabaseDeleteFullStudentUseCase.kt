package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseDeleteContactsUseCase

class DatabaseDeleteFullStudentUseCase(
    private val databaseDeleteStudentUseCase: DatabaseDeleteStudentUseCase,
    private val databaseDeleteContactsUseCase: DatabaseDeleteContactsUseCase
): DatabaseBaseUseCase() {

    suspend fun deleteFullStudent(studentId: String) {
        databaseDeleteContactsUseCase.deleteContacts(studentId)
        databaseDeleteStudentUseCase.deleteStudent(studentId)
    }
}
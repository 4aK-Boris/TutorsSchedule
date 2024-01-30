package dmitriy.losev.database.domain.repositories.students

import dmitriy.losev.core.models.Contact

interface StudentContactsRepository {

    suspend fun getStudentContacts(studentId: String): List<Contact>

    suspend fun saveStudentContacts(studentId: String, contacts: List<Contact>)
}
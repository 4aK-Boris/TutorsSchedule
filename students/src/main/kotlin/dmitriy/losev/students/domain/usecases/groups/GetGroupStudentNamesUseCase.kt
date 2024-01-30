package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseDeleteGroupStudentsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseGetGroupStudentNamesUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseSaveGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentNamesUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetGroupStudentNamesUseCase(
    private val firebaseGetGroupStudentNamesUseCase: FirebaseGetGroupStudentNamesUseCase,
    private val databaseGetGroupStudentNamesUseCase: DatabaseGetGroupStudentNamesUseCase,
    private val databaseSaveGroupStudentsUseCase: DatabaseSaveGroupStudentsUseCase,
    private val databaseDeleteGroupStudentsUseCase: DatabaseDeleteGroupStudentsUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getGroupStudentNames(
        groupId: String,
        onFirebaseLoading: (List<StudentName>) -> Unit,
        onDatabaseLoading: (List<StudentName>) -> Unit
    ): Unit = loadAllData(
        loadFromFirebase = { firebaseGetGroupStudentNamesUseCase.getGroupStudentIds(groupId) },
        loadFromDatabase = { databaseGetGroupStudentNamesUseCase.getGroupStudentNames(groupId) },
        saveToDatabase = { studentNames ->
            val studentIds = convertStudentNamesToStudentIds(studentNames)
            databaseSaveGroupStudentsUseCase.saveGroupStudents(groupId, studentIds)
        },
        deleteFromDatabase = { studentNames ->
            val studentIds = convertStudentNamesToStudentIds(studentNames)
            databaseDeleteGroupStudentsUseCase.deleteGroupStudents(groupId, studentIds)
        },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )

    private fun convertStudentNamesToStudentIds(studentNames: List<StudentName>): List<String> {
        return studentNames.map { studentName -> studentName.id }
    }
}
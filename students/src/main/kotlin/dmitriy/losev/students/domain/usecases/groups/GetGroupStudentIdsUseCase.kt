package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseDeleteGroupStudentsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseGetGroupStudentIdsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseSaveGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentIdsUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetGroupStudentIdsUseCase(
    private val firebaseGetGroupStudentIdsUseCase: FirebaseGetGroupStudentIdsUseCase,
    private val databaseGetGroupStudentIdsUseCase: DatabaseGetGroupStudentIdsUseCase,
    private val databaseSaveGroupStudentsUseCase: DatabaseSaveGroupStudentsUseCase,
    private val databaseDeleteGroupStudentsUseCase: DatabaseDeleteGroupStudentsUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getGroupStudentIds(groupId: String, onFirebaseLoading: (List<String>) -> Unit, onDatabaseLoading: (List<String>) -> Unit): Unit = loadAllData(
        loadFromFirebase = { firebaseGetGroupStudentIdsUseCase.getGroupStudentIds(groupId) },
        loadFromDatabase = { databaseGetGroupStudentIdsUseCase.getGroupStudentIds(groupId) },
        saveToDatabase = { studentIds -> databaseSaveGroupStudentsUseCase.saveGroupStudents(groupId, studentIds) },
        deleteFromDatabase = { studentIds -> databaseDeleteGroupStudentsUseCase.deleteGroupStudents(groupId, studentIds) },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )
}
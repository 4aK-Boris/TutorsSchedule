package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentNamesUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentNamesUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetStudentNamesUseCase(
    private val firebaseGetStudentNamesUseCase: FirebaseGetStudentNamesUseCase,
    private val databaseGetStudentNamesUseCase: DatabaseGetStudentNamesUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getStudentsNames(onFirebaseLoading: (List<StudentName>) -> Unit, onDatabaseLoading: (List<StudentName>) -> Unit): Unit = loadAllDataWithoutSave(
        loadFromFirebase = { firebaseGetStudentNamesUseCase.getStudentNames() },
        loadFromDatabase = { databaseGetStudentNamesUseCase.getStudentNames() },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )

    suspend fun getStudentsNames(
        studentIds: List<String>,
        onFirebaseLoading: (List<StudentName>) -> Unit,
        onDatabaseLoading: (List<StudentName>) -> Unit
    ): Unit = loadAllDataWithoutSave(
        loadFromFirebase = { firebaseGetStudentNamesUseCase.getStudentNames(studentIds) },
        loadFromDatabase = { databaseGetStudentNamesUseCase.getStudentNames(studentIds) },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )
}
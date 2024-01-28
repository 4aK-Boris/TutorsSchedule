package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.database.domain.usecases.students.DatabaseDeleteFullStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseDeleteFullStudentUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class DeleteStudentUseCase(
    private val firebaseDeleteFullStudentUseCase: FirebaseDeleteFullStudentUseCase,
    private val databaseDeleteFullStudentUseCase: DatabaseDeleteFullStudentUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun deleteStudent(studentId: String): Unit = deleteData(
        deleteInFirebase = { firebaseDeleteFullStudentUseCase.deleteFullStudent(studentId) },
        deleteInDatabase = { databaseDeleteFullStudentUseCase.deleteFullStudent(studentId) }
    )
}
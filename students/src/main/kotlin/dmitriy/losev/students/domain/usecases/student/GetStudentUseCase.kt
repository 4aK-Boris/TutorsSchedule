package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Student
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseSaveStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetStudentUseCase(
    private val firebaseGetStudentUseCase: FirebaseGetStudentUseCase,
    private val databaseGetStudentUseCase: DatabaseGetStudentUseCase,
    private val databaseSaveStudentUseCase: DatabaseSaveStudentUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getStudent(studentId: String, onFirebaseLoading: (Student) -> Unit, onDatabaseLoading: (Student?) -> Unit): Unit = loadData(
        loadFromFirebase = { firebaseGetStudentUseCase.getStudent(studentId) },
        loadFromDatabase = { databaseGetStudentUseCase.getStudent(studentId) },
        saveToDatabase = { student -> databaseSaveStudentUseCase.saveStudent(student) },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )

    suspend fun getStudentFromFirebase(studentId: String): Student {
        return firebaseGetStudentUseCase.getStudent(studentId)
    }
}
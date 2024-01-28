package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Student
import dmitriy.losev.database.domain.usecases.students.DatabaseUpdateStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseUpdateStudentUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class UpdateStudentUseCase(
    private val firebaseUpdateStudentUseCase: FirebaseUpdateStudentUseCase,
    private val databaseUpdateStudentUseCase: DatabaseUpdateStudentUseCase,
    private val checkStudentUseCase: CheckStudentUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun updateStudent(studentId: String, student: Student) {
        checkStudentUseCase.checkStudent(student)
        updateData(
            data = student,
            updateInFirebase = { s -> firebaseUpdateStudentUseCase.updateStudent(studentId, s) },
            updateInDatabase = { s -> databaseUpdateStudentUseCase.updateStudent(s) }
        )
    }
}
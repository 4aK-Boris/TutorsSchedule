package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.StudentsRepository

class DatabaseDeleteStudentUseCase(private val studentsRepository: StudentsRepository, ): DatabaseBaseUseCase() {

    suspend fun deleteStudent(studentId: String) {
        studentsRepository.deleteStudent(studentId)
    }
}
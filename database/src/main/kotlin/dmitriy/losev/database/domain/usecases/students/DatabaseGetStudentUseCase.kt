package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.StudentsRepository

class DatabaseGetStudentUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun getStudent(studentId: String): Student? {
        return studentsRepository.getStudent(studentId)
    }
}
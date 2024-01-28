package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.StudentsRepository

class DatabaseUpdateStudentUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun updateStudent(student: Student) {
        studentsRepository.updateStudent(student)
    }
}
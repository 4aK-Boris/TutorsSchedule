package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.StudentsRepository

class DatabaseSaveStudentUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun saveStudent(student: Student) {
        studentsRepository.saveStudent(student)
    }
}
package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentsRepository

class DatabaseGetStudentsUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun getStudents(): List<Student> {
        return studentsRepository.getStudents()
    }
}
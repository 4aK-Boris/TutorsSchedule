package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentsRepository

class DatabaseDeleteStudentsUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun deleteStudents(students: List<Student>) {
        studentsRepository.deleteStudents(students)
    }
}
package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.StudentsRepository

class DatabaseGetStudentsUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun getStudents(): List<Student> {
       val students = studentsRepository.getStudents()
        println(students)
        return students
    }
}
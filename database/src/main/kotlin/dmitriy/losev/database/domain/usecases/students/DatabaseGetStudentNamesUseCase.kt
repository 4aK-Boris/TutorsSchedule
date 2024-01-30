package dmitriy.losev.database.domain.usecases.students

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentsRepository

class DatabaseGetStudentNamesUseCase(private val studentsRepository: StudentsRepository): DatabaseBaseUseCase() {

    suspend fun getStudentNames(): List<StudentName> {
        return studentsRepository.getStudentNames()
    }

    suspend fun getStudentNames(studentIds: List<String>): List<StudentName> {
        return studentsRepository.getStudentNames(studentIds)
    }
}
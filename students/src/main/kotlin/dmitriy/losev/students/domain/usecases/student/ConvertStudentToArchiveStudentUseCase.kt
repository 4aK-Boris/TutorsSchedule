package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.models.Student
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.converters.ArchiveStudentConverter
import dmitriy.losev.students.domain.models.ArchiveStudent

class ConvertStudentToArchiveStudentUseCase(private val archiveStudentConverter: ArchiveStudentConverter): StudentsBaseUseCase() {

    fun convertStudentToArchiveStudent(student: Student): ArchiveStudent {
        return archiveStudentConverter.map(value = student)
    }
}
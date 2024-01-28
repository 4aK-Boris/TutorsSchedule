package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.models.Student
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.converters.ActiveStudentConverter
import dmitriy.losev.students.domain.models.ActiveStudent

class ConvertStudentToActiveStudentUseCase(private val activeStudentConverter: ActiveStudentConverter): StudentsBaseUseCase() {

    fun convertStudentToActiveStudent(student: Student): ActiveStudent {
        return activeStudentConverter.map(value = student)
    }
}
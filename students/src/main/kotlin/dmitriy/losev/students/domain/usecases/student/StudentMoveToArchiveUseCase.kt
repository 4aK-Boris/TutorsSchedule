package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.students.core.StudentsBaseUseCase

class StudentMoveToArchiveUseCase(private val changeStudentTypeUseCase: ChangeStudentTypeUseCase): StudentsBaseUseCase() {

    suspend fun moveToArchive(studentId: String) {
        changeStudentTypeUseCase.changeStudentType(studentId = studentId, studentType = StudentType.ARCHIVE)
    }
}
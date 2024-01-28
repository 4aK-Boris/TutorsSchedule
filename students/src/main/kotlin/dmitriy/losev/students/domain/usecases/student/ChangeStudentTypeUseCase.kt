package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class ChangeStudentTypeUseCase(
    private val firebaseGetStudentUseCase: FirebaseGetStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun changeStudentType(studentId: String, studentType: StudentType) {
        val student = firebaseGetStudentUseCase.getStudent(studentId).copy(studentType = studentType)
        updateStudentUseCase.updateStudent(studentId, student)
    }
}
package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.models.Student
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckEmailUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckNameUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase

class CheckStudentUseCase(
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase,
    private val studentsCheckEmailUseCase: StudentsCheckEmailUseCase,
    private val studentsCheckNameUseCase: StudentsCheckNameUseCase
): StudentsBaseUseCase() {

    suspend fun checkStudent(student: Student) {
        studentsCheckNameUseCase.checkFirstName(student.firstName)
        studentsCheckPhoneNumberUseCase.checkPhoneNumber(student.phoneNumber)
        studentsCheckEmailUseCase.checkEmail(student.email)
    }
}
package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableStudentException
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentRepository

class FirebaseStudentUseCase(
    errorHandler: ErrorHandler,
    private val firebaseStudentRepository: FirebaseStudentRepository,
    private val firebaseUserUseCase: FirebaseUserUseCase
): FirebaseBaseUseCase(errorHandler) {

    suspend fun getStudent(studentId: String): Result<Student> = safeReturnCall {
        firebaseUserUseCase.getUserWithException().processingResult { user ->
            getStudent(user, studentId)
        }
    }

    suspend fun getStudent(user: FirebaseUser, studentId: String): Result<Student> = safeCall {
        firebaseStudentRepository.getStudent(teacherUId = user.uid, studentId = studentId) ?: throw NullableStudentException()
    }
}
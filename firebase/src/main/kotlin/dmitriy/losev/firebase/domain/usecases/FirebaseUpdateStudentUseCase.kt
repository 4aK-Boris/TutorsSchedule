package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentRepository

class FirebaseUpdateStudentUseCase(
    errorHandler: ErrorHandler,
    private val firebaseStudentRepository: FirebaseStudentRepository,
    private val firebaseUserUseCase: FirebaseUserUseCase
): FirebaseBaseUseCase(errorHandler) {

    suspend fun updateStudent(student: Student): Result<Unit> = safeReturnCall {
        firebaseUserUseCase.getUserWithException().processingResult { user ->
            updateStudent(user, student)
        }
    }

    suspend fun updateStudent(user: FirebaseUser, student: Student): Result<Unit> = safeCall {
        firebaseStudentRepository.updateStudent(teacherUId = user.uid, student = student)
    }
}
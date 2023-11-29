package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentRepository

class FirebaseAddStudentUseCase(
    errorHandler: ErrorHandler,
    private val firebaseStudentRepository: FirebaseStudentRepository,
    private val firebaseUserUseCase: FirebaseUserUseCase
): FirebaseBaseUseCase(errorHandler) {

    suspend fun addStudent(student: Student): Result<Unit> = safeReturnCall {
        firebaseUserUseCase.getUserWithException().processingResult { user ->
            addStudent(user, student)
        }
    }

    suspend fun addStudent(user: FirebaseUser, student: Student): Result<Unit> = safeCall {
        firebaseStudentRepository.addStudent(teacherUId = user.uid, student = student)
    }
}
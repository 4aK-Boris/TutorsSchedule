package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.models.StudentType
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentsRepository

class FirebaseStudentsUseCase(
    errorHandler: ErrorHandler,
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseUserUseCase: FirebaseUserUseCase
): FirebaseBaseUseCase(errorHandler) {

    suspend fun getStudents(studentType: StudentType): Result<List<Student>> = safeReturnCall {
        firebaseUserUseCase.getUserWithException().processingResult { user ->
            getStudents(user, studentType)
        }
    }

    suspend fun getStudents(user: FirebaseUser, studentType: StudentType): Result<List<Student>> = safeCall {
        firebaseStudentsRepository.getStudents(teacherUid = user.uid, studentType = studentType)
    }
}
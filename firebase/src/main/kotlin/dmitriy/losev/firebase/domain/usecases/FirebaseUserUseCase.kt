package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException

class FirebaseUserUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
) : FirebaseBaseUseCase(errorHandler = errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun getUserWithException(): Result<FirebaseUser> = safeCall {
        user ?: throw UserNotAuthorizationException()
    }

    suspend fun getUserWithoutException(): Result<FirebaseUser> = safeCallNullable {
        user
    }
}
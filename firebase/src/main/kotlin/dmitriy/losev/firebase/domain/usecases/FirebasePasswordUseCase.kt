package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import kotlinx.coroutines.tasks.await

class FirebasePasswordUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth,
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun changePassword(password: String): Result<Unit> = safeCall {
        user?.updatePassword(password)?.await() ?: UserNotAuthorizationException()
    }
}
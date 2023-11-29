package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.core.result.emptyResult
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUpdateEmailUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth,
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun updateEmail(email: String?): Result<Unit> = safeCall {
        email?.let { user?.updateEmail(email)?.await() } ?: emptyResult
    }

    suspend fun updateEmail(user: FirebaseUser, email: String?): Result<Unit> = safeCall {
        email?.let { user.updateEmail(email).await() } ?: emptyResult
    }
}
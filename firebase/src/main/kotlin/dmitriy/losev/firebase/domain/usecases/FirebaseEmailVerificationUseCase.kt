package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseEmailVerificationUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun sendVerificationMessage(user: FirebaseUser): Result<Unit> = safeCall {
        user.sendEmailVerification()
    }

    suspend fun sendVerificationMessage(): Result<Unit> = safeCall {
        user?.sendEmailVerification()
    }

    suspend fun verifyEmailBeforeUpdate(user: FirebaseUser, email: String): Result<Unit> = safeCall {
        user.verifyBeforeUpdateEmail(email).await()
    }

    suspend fun verifyEmailBeforeUpdate(email: String): Result<Unit> = safeCall {
        user?.verifyBeforeUpdateEmail(email)?.await()
    }
}
package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseResetPasswordUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
): FirebaseBaseUseCase(errorHandler) {

    suspend fun sendPasswordResetEmail(email: String): Result<Unit> = safeCall {
        auth.setLanguageCode("ru")
        auth.sendPasswordResetEmail(email).await()
    }
}
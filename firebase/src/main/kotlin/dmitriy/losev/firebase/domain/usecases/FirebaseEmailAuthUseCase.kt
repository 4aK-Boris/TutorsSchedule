package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseEmailAuthUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
): FirebaseBaseUseCase(errorHandler) {

    suspend fun authWithEmail(email: String, password: String): Result<Unit> = safeCall {
        auth.signInWithEmailAndPassword(email, password).await()
    }
}
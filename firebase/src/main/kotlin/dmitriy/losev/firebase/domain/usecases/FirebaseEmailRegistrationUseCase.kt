package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseEmailRegistrationUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
): FirebaseBaseUseCase(errorHandler) {

    suspend fun registrationWithEmail(email: String, password: String): Result<FirebaseUser> = safeCall {
        auth.createUserWithEmailAndPassword(email, password).await().user ?: throw Exception()
    }
}
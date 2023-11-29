package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseEmailUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun getEmail(): Result<String> = safeCallNullable {
        user?.email
    }

    suspend fun getEmail(user: FirebaseUser): Result<String> = safeCallNullable {
        user.email
    }
}
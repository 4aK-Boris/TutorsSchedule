package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseDisplayNameUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun getDisplayName(): Result<String> = safeCallNullable {
        user?.displayName
    }

    suspend fun getDisplayName(user: FirebaseUser): Result<String> = safeCallNullable {
        user.displayName
    }
}
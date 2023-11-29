package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseFirstNameUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth,
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun getFirstName(): Result<String> = safeCallNullable {
        val displayName = user?.displayName
        if (displayName != null && displayName[0] != SPACE) {
            displayName.substringBefore(delimiter = SPACE)
        } else {
            null
        }
    }

    suspend fun getFirstName(user: FirebaseUser): Result<String> = safeCallNullable {
        val displayName = user.displayName
        if (displayName != null && displayName[0] != SPACE) {
            displayName.substringBefore(delimiter = SPACE)
        } else {
            null
        }
    }

    companion object {
        private const val SPACE = ' '
    }
}
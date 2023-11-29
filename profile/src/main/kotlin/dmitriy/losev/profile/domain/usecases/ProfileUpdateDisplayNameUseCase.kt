package dmitriy.losev.profile.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateDisplayNameUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateDisplayNameUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUpdateDisplayNameUseCase: FirebaseUpdateDisplayNameUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun updateDisplayName(
        user: FirebaseUser,
        firstName: String?,
        lastName: String?
    ): Result<Unit> = safeReturnCall {
        firebaseUpdateDisplayNameUseCase.updateDisplayName(
            user = user,
            firstName = firstName ?: EMPTY_STRING,
            lastName = lastName ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
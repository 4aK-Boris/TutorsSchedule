package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateAllUseCase(
    errorHandler: ErrorHandler,
    private val profileCheckEmailUseCase: ProfileCheckEmailUseCase,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun updateAll(
        user: FirebaseUser,
        avatarUri: Uri?,
        firstName: String,
        lastName: String,
        email: String
    ): Result<Unit> = safeReturnCall {
        profileCheckEmailUseCase.checkEmailForValidation(email).processingResult {
            firebaseEmailVerificationUseCase.verifyEmailBeforeUpdate(user, email)
        }
    }
}
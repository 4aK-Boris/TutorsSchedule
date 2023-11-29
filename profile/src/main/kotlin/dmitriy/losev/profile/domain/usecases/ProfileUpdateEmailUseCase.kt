package dmitriy.losev.profile.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateEmailUseCase(
    errorHandler: ErrorHandler,
    private val profileCheckEmailUseCase: ProfileCheckEmailUseCase,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun updateEmail(user: FirebaseUser, email: String): Result<Unit> = safeReturnCall {
        profileCheckEmailUseCase.checkEmailForValidation(email).processingResult {
            firebaseEmailVerificationUseCase.verifyEmailBeforeUpdate(user, email)
        }
    }
}
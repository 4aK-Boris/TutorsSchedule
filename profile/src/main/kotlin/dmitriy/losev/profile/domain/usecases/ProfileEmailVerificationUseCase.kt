package dmitriy.losev.profile.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileEmailVerificationUseCase(
    errorHandler: ErrorHandler,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun sendEmailVerification(user: FirebaseUser): Result<Unit> = safeReturnCall {
        firebaseEmailVerificationUseCase.sendVerificationMessage(user)
    }
}
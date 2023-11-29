package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseResetPasswordUseCase

class AuthenticationPasswordResetUseCase(
    errorHandler: ErrorHandler,
    private val authenticationEmailUseCase: AuthenticationEmailUseCase,
    private val firebaseResetPasswordUseCase: FirebaseResetPasswordUseCase
) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun resetPassword(email: String): Result<Unit> = safeReturnCall {
        authenticationEmailUseCase.checkEmailValidationForResetPassword(email).processingResult {
            firebaseResetPasswordUseCase.sendPasswordResetEmail(email)
        }
    }
}
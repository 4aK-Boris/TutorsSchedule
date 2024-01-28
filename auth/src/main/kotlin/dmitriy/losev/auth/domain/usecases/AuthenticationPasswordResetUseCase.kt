package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseResetPasswordUseCase

class AuthenticationPasswordResetUseCase(
    private val authenticationEmailUseCase: AuthenticationEmailUseCase,
    private val firebaseResetPasswordUseCase: FirebaseResetPasswordUseCase
) : AuthenticationBaseUseCase() {

    suspend fun resetPassword(email: String) {
        authenticationEmailUseCase.checkEmailValidationForResetPassword(email)
        firebaseResetPasswordUseCase.sendPasswordResetEmail(email)
    }
}
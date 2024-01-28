package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailAuthUseCase

class AuthenticationEmailAuthUseCase(
    private val authenticationEmailUseCase: AuthenticationEmailUseCase,
    private val authenticationPasswordUseCase: AuthenticationPasswordUseCase,
    private val firebaseEmailAuthUseCase: FirebaseEmailAuthUseCase
) {

    suspend fun authWithEmail(email: String, password: String) {
        authenticationEmailUseCase.checkEmailValidationForLogin(email)
        authenticationPasswordUseCase.checkPassword(password)
        firebaseEmailAuthUseCase.authWithEmail(email, password)
    }
}
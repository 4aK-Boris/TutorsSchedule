package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailRegistrationUseCase

class AuthenticationRegistrationUseCase(
    errorHandler: ErrorHandler,
    private val authenticationPasswordUseCase: AuthenticationPasswordUseCase,
    private val authenticationUpdateInformationUseCase: AuthenticationUpdateInformationUseCase,
    private val firebaseEmailRegistrationUseCase: FirebaseEmailRegistrationUseCase
) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun registration(userDescription: UserDescription, password1: String, password2: String) = safeReturnCall {
        authenticationPasswordUseCase.checkPassword(password1, password2).processingResult {
            firebaseEmailRegistrationUseCase.registrationWithEmail(userDescription.email, password1).processingResult { user ->
                authenticationUpdateInformationUseCase.updateInformation(user, userDescription)
            }
        }
    }
}
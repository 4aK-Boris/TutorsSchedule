package dmitriy.losev.auth.domain.usecases.screens

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationValidateUseCases
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseAuthUseCases

class PasswordResetScreenUseCases(
    errorHandler: ErrorHandler,
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationValidateUseCases: AuthenticationValidateUseCases
) : AuthenticationBaseUseCase(errorHandler = errorHandler) {

    suspend fun resetPassword(email: String, navController: NavController): Result<Unit> =
        safeReturnCall {
            authenticationValidateUseCases.isValidateEmail(email = email).processingResult {
                firebaseAuthUseCases.sendPasswordResetEmail(email = email)
                    .processingResult {
                        authenticationNavigationUseCases.navigateToLoginScreen(navController = navController)
                    }
            }
        }
}

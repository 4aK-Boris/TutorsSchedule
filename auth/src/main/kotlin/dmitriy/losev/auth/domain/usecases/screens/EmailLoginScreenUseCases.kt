package dmitriy.losev.auth.domain.usecases.screens

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationValidateUseCases
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseAuthUseCases

class EmailLoginScreenUseCases(
    errorHandler: ErrorHandler,
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationValidateUseCases: AuthenticationValidateUseCases
) :
    AuthenticationBaseUseCase(errorHandler = errorHandler) {

    suspend fun authWithEmail(
        email: String,
        password: String,
        navController: NavController
    ): Result<Unit> = safeReturnCall {
        authenticationValidateUseCases.isValidateEmail(email = email).processingResult {
            firebaseAuthUseCases.authWithEmail(email = email, password = password)
                .processingResult {
                    authenticationNavigationUseCases.navigateToEmptyScreen(navController = navController)
                }
        }
    }

    suspend fun navigateToPasswordResetScreen(navController: NavController, email: String) = safeReturnCall {
        authenticationNavigationUseCases.navigateToPasswordResetScreen(navController = navController, email = email)
    }
}
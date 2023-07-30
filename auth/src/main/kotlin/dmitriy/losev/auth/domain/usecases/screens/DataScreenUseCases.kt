package dmitriy.losev.auth.domain.usecases.screens

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationValidateUseCases
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription

class DataScreenUseCases(
    errorHandler: ErrorHandler,
    private val authenticationValidateUseCases: AuthenticationValidateUseCases,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) :
    AuthenticationBaseUseCase(errorHandler = errorHandler) {

    suspend fun navigateToPasswordScreen(
        userDescription: UserDescription,
        navController: NavController
    ): Result<Unit> = safeReturnCall(
        call1 = { authenticationValidateUseCases.isValidateFirstName(firstName = userDescription.firstName) },
        call2 = { authenticationValidateUseCases.isValidateLastName(lastName = userDescription.lastName) },
        call3 = { authenticationValidateUseCases.isValidateEmail(email = userDescription.email) }).processingResult {
        authenticationNavigationUseCases.navigateToPasswordScreen(
            userDescription = userDescription,
            navController = navController
        )
    }
}
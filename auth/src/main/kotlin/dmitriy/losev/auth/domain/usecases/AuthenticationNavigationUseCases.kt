package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.models.UserDescription

class AuthenticationNavigationUseCases(errorHandler: ErrorHandler) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun navigateToLoginScreen(authenticationNavigationListener: AuthenticationNavigationListener): Result<Unit> = safeCall {
        authenticationNavigationListener.navigateToLoginScreen()
    }

    suspend fun navigateToDataScreen(authenticationNavigationListener: AuthenticationNavigationListener): Result<Unit> = safeCall {
        authenticationNavigationListener.navigateToDataScreen()
    }


    suspend fun navigateToPasswordScreen(
        authenticationNavigationListener: AuthenticationNavigationListener,
        userDescription: UserDescription
    ): Result<Unit> = safeCall {
        authenticationNavigationListener.navigateToPasswordScreen(userDescription)
    }

    suspend fun navigateToPasswordResetScreen(authenticationNavigationListener: AuthenticationNavigationListener, email: String?): Result<Unit> = safeCall {
        authenticationNavigationListener.navigateToPasswordResetScreen(email)
    }

    suspend fun navigateToProfileScreen(authenticationNavigationListener: AuthenticationNavigationListener): Result<Unit> = safeCall {
        authenticationNavigationListener.navigateToProfileScreen()
    }
}
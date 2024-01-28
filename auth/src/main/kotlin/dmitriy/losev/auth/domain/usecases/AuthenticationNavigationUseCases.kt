package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.AuthenticationNavigationListener

class AuthenticationNavigationUseCases : AuthenticationBaseUseCase() {

    suspend fun navigateToLoginScreen(authenticationNavigationListener: AuthenticationNavigationListener) {
        authenticationNavigationListener.navigateToLoginScreen()
    }

    suspend fun navigateToRegistrationScreen(authenticationNavigationListener: AuthenticationNavigationListener) {
        authenticationNavigationListener.navigateToRegistrationScreen()
    }


    suspend fun navigateToPasswordScreen(
        authenticationNavigationListener: AuthenticationNavigationListener,
        firstName: String,
        lastName: String,
        patronymic: String,
        email: String
    ) {
        val nullableFirstName = firstName.ifBlank { null }
        val nullableLastName = lastName.ifBlank { null }
        val nullablePatronymic = patronymic.ifBlank { null }
        authenticationNavigationListener.navigateToPasswordScreen(nullableFirstName, nullableLastName, nullablePatronymic, email)
    }

    suspend fun navigateToPasswordResetScreen(authenticationNavigationListener: AuthenticationNavigationListener, email: String?) {
        authenticationNavigationListener.navigateToPasswordResetScreen(email)
    }

    suspend fun navigateToProfileScreen(authenticationNavigationListener: AuthenticationNavigationListener) {
        authenticationNavigationListener.navigateToProfileScreen()
    }

    suspend fun back(authenticationNavigationListener: AuthenticationNavigationListener) {
        authenticationNavigationListener.back()
    }
}
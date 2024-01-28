package dmitriy.losev.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.ui.screens.LoginScreen
import dmitriy.losev.auth.presentation.ui.screens.PasswordResetScreen
import dmitriy.losev.auth.presentation.ui.screens.PasswordScreen
import dmitriy.losev.auth.presentation.ui.screens.RegistrationScreen
import dmitriy.losev.auth.presentation.ui.screens.StartScreen

fun NavGraphBuilder.authenticationNavigation(
    route: String,
    client: SignInClient,
    authenticationNavigationListener: AuthenticationNavigationListener,
    startDestination: String = AuthenticationScreens.StartScreen.route
) {
    navigation(
        startDestination = startDestination,
        route = route
    ) {

        composable(
            route = AuthenticationScreens.StartScreen.route
        ) {
            StartScreen(authenticationNavigationListener)
        }

        composable(
            route = AuthenticationScreens.LoginScreen.route
        ) {
            LoginScreen(authenticationNavigationListener, client)
        }

        composable(
            route = AuthenticationScreens.RegistrationScreen.route
        ) {
            RegistrationScreen(authenticationNavigationListener)
        }

        composable(
            route = AuthenticationScreens.PasswordScreen.route,
            arguments = AuthenticationScreens.PasswordScreen.arguments
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString(AuthenticationScreens.PasswordScreen.FIRST_NAME)
            val lastName = backStackEntry.arguments?.getString(AuthenticationScreens.PasswordScreen.LAST_NAME)
            val patronymic = backStackEntry.arguments?.getString(AuthenticationScreens.PasswordScreen.PATRONYMIC)
            val email = backStackEntry.arguments?.getString(AuthenticationScreens.PasswordScreen.EMAIL)
            requireNotNull(email) { "email parameter wasn't found. Please make sure it's set!" }
            PasswordScreen(firstName, lastName, patronymic, email, authenticationNavigationListener)
        }

        composable(
            route = AuthenticationScreens.PasswordResetScreen.route,
            arguments = AuthenticationScreens.PasswordResetScreen.arguments
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(AuthenticationScreens.PasswordResetScreen.EMAIL)
            PasswordResetScreen(authenticationNavigationListener, email)
        }
    }
}
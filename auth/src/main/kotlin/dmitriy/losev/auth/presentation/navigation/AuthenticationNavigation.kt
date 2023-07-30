package dmitriy.losev.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.presentation.ui.screens.DataScreen
import dmitriy.losev.auth.presentation.ui.screens.EmailLoginScreen
import dmitriy.losev.auth.presentation.ui.screens.PasswordResetScreen
import dmitriy.losev.auth.presentation.ui.screens.PasswordScreen
import dmitriy.losev.auth.presentation.ui.screens.StartScreen
import dmitriy.losev.firebase.domain.models.UserDescription
import kotlinx.serialization.json.Json
import ru.mpei.authentication.presentation.navigation.AuthenticationScreens

fun NavGraphBuilder.authenticationNavigation(
    navController: NavController,
    route: String,
    client: SignInClient,
    startDestination: String = AuthenticationScreens.StartScreen.route
) {
    navigation(
        startDestination = startDestination,
        route = route
    ) {

        composable(
            route = AuthenticationScreens.StartScreen.route
        ) {
            StartScreen(navController = navController, client = client)
        }

        composable(
            route = AuthenticationScreens.LoginScreen.route
        ) {
            EmailLoginScreen(navController = navController)
        }

        composable(
            route = AuthenticationScreens.DataScreen.route
        ) {
            DataScreen(navController = navController)
        }

        composable(
            route = AuthenticationScreens.PasswordScreen.route,
            arguments = AuthenticationScreens.PasswordScreen.arguments
        ) { backStackEntry ->
            val userDescription: UserDescription? =
                backStackEntry.arguments?.getString(AuthenticationScreens.PasswordScreen.USER_DESCRIPTION)
                    ?.let { Json.decodeFromString(it) }
            requireNotNull(userDescription) { "userDescription parameter wasn't found. Please make sure it's set!" }
            PasswordScreen(navController = navController, userDescription = userDescription)
        }

        composable(
            route = AuthenticationScreens.PasswordResetScreen.route,
            arguments = AuthenticationScreens.PasswordResetScreen.arguments
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(AuthenticationScreens.PasswordResetScreen.EMAIL)
            requireNotNull(email) { "email parameter wasn't found. Please make sure it's set!" }
            PasswordResetScreen(navController = navController, inputEmail = email)
        }
    }
}
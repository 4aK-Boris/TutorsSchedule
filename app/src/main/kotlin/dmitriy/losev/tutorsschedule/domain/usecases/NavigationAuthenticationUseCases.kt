package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.auth.presentation.navigation.AuthenticationScreens
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.firebase.domain.models.UserDescription

class NavigationAuthenticationUseCases {

    suspend fun navigateToLoginScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = AuthenticationScreens.LoginScreen.route)
    }


    suspend fun navigateToDataScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = AuthenticationScreens.DataScreen.route)
    }


    suspend fun navigateToPasswordScreen(
        navController: NavController,
        userDescription: UserDescription
    ) = switchOnMain {
        navController.navigate(
            route = AuthenticationScreens.PasswordScreen.createRoute(
                userDescription = userDescription
            )
        )
    }


    suspend fun navigateToPasswordResetScreen(navController: NavController, email: String?) =
        switchOnMain {
            navController.navigate(AuthenticationScreens.PasswordResetScreen.createRoute(email))
        }

}
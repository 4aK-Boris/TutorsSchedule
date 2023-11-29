package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.tutorsschedule.presentation.navigation.Screens

class NavigationUseCases {
    suspend fun navigateProfileScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = Screens.ProfileScreen.route)
    }

    suspend fun navigateToAuthenticationsScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = Screens.AuthenticationScreen.route)
    }

    suspend fun navigateToDestination(navController: NavController, route: String) = switchOnMain {
        navController.navigate(route = route)
    }

    suspend fun clearAuthenticationScreen(navController: NavController) = switchOnMain {
        navController.clearBackStack(Screens.AuthenticationScreen.route)
    }

}
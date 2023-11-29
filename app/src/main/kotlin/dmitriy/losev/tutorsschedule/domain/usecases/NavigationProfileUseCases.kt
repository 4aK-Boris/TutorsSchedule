package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.profile.presentation.ui.navigation.ProfileScreens

class NavigationProfileUseCases {

    suspend fun navigateToProfileScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.ProfileScreen.route)
    }

    suspend fun navigateToChangePasswordScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.ChangePasswordScreen.route)
    }

    suspend fun navigateToEditProfileScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.EditProfileScreen.route)
    }
}
package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.switchOnMain
import dmitriy.losev.profile.presentation.ui.navigation.ProfileScreens
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase

class NavigationProfileUseCases: AppBaseUseCase() {

    suspend fun navigateToProfileScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.ProfileScreen.route)
    }

    suspend fun navigateToChangePasswordScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.ChangePasswordScreen.route)
    }

    suspend fun navigateToChangeEmailScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.ChangeEmailScreen.route)
    }

    suspend fun navigateToEditProfileScreen(navController: NavController, uri: String?) = switchOnMain {
        navController.navigate(route = ProfileScreens.EditProfileScreen.createRoute(uri))
    }

    suspend fun navigateToSettingsScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.SettingsScreen.route)
    }

    suspend fun navigateToCameraScreen(navController: NavController) = switchOnMain {
        navController.navigate(route = ProfileScreens.CameraScreen.route)
    }
}
package dmitriy.losev.profile.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.screens.CameraScreen
import dmitriy.losev.profile.presentation.ui.screens.ChangeEmailScreen
import dmitriy.losev.profile.presentation.ui.screens.ChangePasswordScreen
import dmitriy.losev.profile.presentation.ui.screens.EditProfileScreen
import dmitriy.losev.profile.presentation.ui.screens.ProfileScreen
import dmitriy.losev.profile.presentation.ui.screens.SettingsScreen

fun NavGraphBuilder.profileNavigation(
    profileNavigationListener: ProfileNavigationListener,
    route: String,
    startDestination: String = ProfileScreens.ProfileScreen.route
) {
    navigation(
        startDestination = startDestination,
        route = route
    ) {

        composable(
            route = ProfileScreens.ProfileScreen.route
        ) {
            ProfileScreen(profileNavigationListener)
        }

        composable(
            route = ProfileScreens.EditProfileScreen.route,
            arguments = ProfileScreens.EditProfileScreen.arguments
        ) { backStackEntry ->
            val uri = backStackEntry.arguments?.getString(ProfileScreens.EditProfileScreen.URI)
            EditProfileScreen(profileNavigationListener, uri)
        }

        composable(
            route = ProfileScreens.ChangePasswordScreen.route
        ) {
            ChangePasswordScreen(profileNavigationListener)
        }

        composable(
            route = ProfileScreens.ChangeEmailScreen.route
        ) {
            ChangeEmailScreen(profileNavigationListener)
        }

        composable(
            route = ProfileScreens.SettingsScreen.route
        ) {
            SettingsScreen(profileNavigationListener)
        }

        composable(
            route = ProfileScreens.CameraScreen.route
        ) {
            CameraScreen(profileNavigationListener)
        }
    }
}
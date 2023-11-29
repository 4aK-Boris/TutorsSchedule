package dmitriy.losev.profile.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.screens.ChangePasswordScreen
import dmitriy.losev.profile.presentation.ui.screens.EditProfileScreen
import dmitriy.losev.profile.presentation.ui.screens.ProfileScreen

fun NavGraphBuilder.profileNavigation(
    profileNavigationListener: ProfileNavigationListener,
    route: String,
    openDrawer: () -> Unit,
    startDestination: String = ProfileScreens.ProfileScreen.route
) {
    navigation(
        startDestination = startDestination,
        route = route
    ) {

        composable(
            route = ProfileScreens.ProfileScreen.route
        ) {
            ProfileScreen(profileNavigationListener, openDrawer)
        }

        composable(
            route = ProfileScreens.EditProfileScreen.route
        ) {
            EditProfileScreen(profileNavigationListener)
        }

        composable(
            route = ProfileScreens.ChangePasswordScreen.route
        ) {
            ChangePasswordScreen(profileNavigationListener)
        }
    }
}
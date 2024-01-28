package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.switchOnMain
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase
import dmitriy.losev.tutorsschedule.presentation.navigation.Screens

class NavigationMainUseCases: AppBaseUseCase() {
    suspend fun navigateProfileScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = Screens.ProfileScreen.route)
    }

    suspend fun navigateToAuthenticationsScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = Screens.AuthenticationScreen.route)
    }

    suspend fun navigateToStudentsScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = Screens.StudentsScreen.route)
    }
}
package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.auth.presentation.navigation.AuthenticationScreens
import dmitriy.losev.core.switchOnMain
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase

class NavigationAuthenticationUseCases: AppBaseUseCase() {

    suspend fun navigateToLoginScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = AuthenticationScreens.LoginScreen.route)
    }


    suspend fun navigateToRegistrationScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = AuthenticationScreens.RegistrationScreen.route)
    }


    suspend fun navigateToPasswordScreen(navController: NavController, firstName: String?, lastName: String?, patronymic: String?, email: String): Unit =
        switchOnMain {
            navController.navigate(route = AuthenticationScreens.PasswordScreen.createRoute(firstName, lastName, patronymic, email))
        }


    suspend fun navigateToPasswordResetScreen(navController: NavController, email: String?) = switchOnMain {
        navController.navigate(AuthenticationScreens.PasswordResetScreen.createRoute(email))
    }
}
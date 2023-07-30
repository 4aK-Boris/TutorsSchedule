package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase
import dmitriy.losev.tutorsschedule.presentation.navigation.Screens

class NavigationUseCases(
    errorHandler: ErrorHandler
): AppBaseUseCase(errorHandler = errorHandler) {
    suspend fun navigateEmptyScreen(navController: NavController): Result<Unit> = safeCall {
        switchOnMain {
            navController.navigate(route = Screens.EmptyScreen.route)
        }
    }

    suspend fun navigateToAuthenticationsScreen(navController: NavController): Result<Unit> = safeCall {
        switchOnMain {
            navController.navigate(route = Screens.AuthenticationScreen.route)
        }
    }
}
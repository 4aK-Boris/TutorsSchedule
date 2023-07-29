package dmitriy.losev.auth.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import ru.mpei.authentication.presentation.navigation.AuthenticationScreens

abstract class AuthenticationNavigationUseCases(
    errorHandler: ErrorHandler
) :
    AuthenticationBaseUseCase(errorHandler = errorHandler) {

    suspend fun navigateToLoginScreen(navController: NavController): Result<Unit> = safeCall {
        switchOnMain {
            navController.navigate(route = AuthenticationScreens.LoginScreen.route)
        }
    }

    suspend fun navigateToDataScreen(navController: NavController): Result<Unit> = safeCall {
        switchOnMain {
            navController.navigate(route = AuthenticationScreens.DataScreen.route)
        }
    }


    suspend fun navigateToPasswordScreen(
        userDescription: UserDescription,
        navController: NavController
    ) = safeCall {
        switchOnMain {
            navController.navigate(route = AuthenticationScreens.PasswordScreen.createRoute(userDescription = userDescription))
        }
    }

    suspend fun navigateToPasswordResetScreen(navController: NavController, email: String): Result<Unit> =
        safeCall {
            switchOnMain {
                navController.navigate(route = AuthenticationScreens.PasswordResetScreen.createRoute(email = email))
            }
        }

    abstract suspend fun navigateToEmptyScreen(navController: NavController): Result<Unit>
}
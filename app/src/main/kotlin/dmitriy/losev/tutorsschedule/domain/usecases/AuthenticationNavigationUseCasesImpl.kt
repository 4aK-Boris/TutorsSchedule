package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.core.exception.ErrorHandler

class AuthenticationNavigationUseCasesImpl(
    errorHandler: ErrorHandler,
    private val navigationUseCases: NavigationUseCases
) : AuthenticationNavigationUseCases(
    errorHandler = errorHandler
) {
    override suspend fun navigateToEmptyScreen(navController: NavController): Result<Unit> =
        safeReturnCall {
            switchOnMain {
                navigationUseCases.navigateEmptyScreen(navController = navController)
            }
        }
}
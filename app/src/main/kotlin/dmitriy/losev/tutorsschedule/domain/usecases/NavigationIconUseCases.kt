package dmitriy.losev.tutorsschedule.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase

class NavigationIconUseCases(
    errorHandler: ErrorHandler,
    private val profileNavigationUseCases: ProfileNavigationUseCases
) : AppBaseUseCase(errorHandler = errorHandler) {

    suspend fun profileNavigationIcon(profileNavigationListener: ProfileNavigationListener): Result<Unit> =
        safeReturnCall {
            profileNavigationUseCases.navigateToEditProfileScreen(profileNavigationListener)
        }
}
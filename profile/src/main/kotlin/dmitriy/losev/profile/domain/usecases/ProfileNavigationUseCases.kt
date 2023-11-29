package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.core.ProfileNavigationListener

class ProfileNavigationUseCases(
    errorHandler: ErrorHandler
): ProfileBaseUseCase(errorHandler) {

    suspend fun navigateToProfileScreen(profileNavigationListener: ProfileNavigationListener): Result<Unit> = safeCall {
        profileNavigationListener.navigateToProfileScreen()
    }

    suspend fun navigateToEditProfileScreen(profileNavigationListener: ProfileNavigationListener): Result<Unit>  = safeCall {
        profileNavigationListener.navigateToEditProfileScreen()
    }

    suspend fun navigateToChangePasswordScreen(profileNavigationListener: ProfileNavigationListener): Result<Unit>  = safeCall {
        profileNavigationListener.navigateToChangePasswordScreen()
    }

    suspend fun navigateToAuthenticationScreen(profileNavigationListener: ProfileNavigationListener): Result<Unit>  = safeCall {
        profileNavigationListener.navigateToAuthenticationScreen()
    }
}
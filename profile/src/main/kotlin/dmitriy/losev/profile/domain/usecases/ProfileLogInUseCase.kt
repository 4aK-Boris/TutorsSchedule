package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.core.ProfileNavigationListener

class ProfileLogInUseCase(errorHandler: ErrorHandler): ProfileBaseUseCase(errorHandler) {

    suspend fun logIn(profileNavigationListener: ProfileNavigationListener): Result<Unit> = safeCall {
        profileNavigationListener.navigateToAuthenticationScreen()
    }
}
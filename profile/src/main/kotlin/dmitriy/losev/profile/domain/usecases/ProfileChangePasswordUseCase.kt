package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebasePasswordUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.core.ProfileNavigationListener

class ProfileChangePasswordUseCase(
    errorHandler: ErrorHandler,
    private val firebasePasswordUseCase: FirebasePasswordUseCase,
    private val profileCheckPasswordUseCase: ProfileCheckPasswordUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases
) : ProfileBaseUseCase(errorHandler) {

    suspend fun changePassword(
        password1: String,
        password2: String,
        profileNavigationListener: ProfileNavigationListener
    ): Result<Unit> = safeReturnCall {
        profileCheckPasswordUseCase.checkPassword(password1, password2).processingResult {
            firebasePasswordUseCase.changePassword(password1).processingResult {
                profileNavigationUseCases.navigateToEditProfileScreen(profileNavigationListener)
            }
        }
    }
}
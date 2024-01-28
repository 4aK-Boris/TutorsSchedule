package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.core.ProfileNavigationListener

class ProfileNavigationUseCases(private val convertUriUseCase: ProfileConvertUriUseCase) : ProfileBaseUseCase() {

    suspend fun navigateToProfileScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigateToProfileScreen()
    }

    suspend fun navigateToEditProfileScreen(profileNavigationListener: ProfileNavigationListener, uri: Uri?) {
        val stringUri = convertUriUseCase.convertUriToString(uri)
        profileNavigationListener.navigateToEditProfileScreen(stringUri)
    }

    suspend fun navigateToChangePasswordScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigateToChangePasswordScreen()
    }

    suspend fun navigateToChangeEmailScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigateToChangeEmailScreen()
    }

    suspend fun navigateToSettingsScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigateToSettingsScreen()
    }

    suspend fun navigateToCameraScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigateToCameraScreen()
    }

    suspend fun navigateToAuthenticationScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigateToAuthenticationScreen()
    }

    suspend fun navigateToSubjectsScreen(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.navigationToSubjectsScreen()
    }

    suspend fun back(profileNavigationListener: ProfileNavigationListener) {
        profileNavigationListener.back()
    }
}
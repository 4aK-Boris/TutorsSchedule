package dmitriy.losev.profile.core

interface ProfileNavigationListener {

    suspend fun navigateToProfileScreen()

    suspend fun navigateToEditProfileScreen(uri: String?)

    suspend fun navigateToChangePasswordScreen()

    suspend fun navigateToChangeEmailScreen()

    suspend fun navigateToSettingsScreen()

    suspend fun navigateToCameraScreen()

    suspend fun navigateToAuthenticationScreen()

    suspend fun navigationToSubjectsScreen()

    suspend fun back()
}
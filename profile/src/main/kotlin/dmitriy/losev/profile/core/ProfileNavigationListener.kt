package dmitriy.losev.profile.core

interface ProfileNavigationListener {

    suspend fun navigateToProfileScreen()

    suspend fun navigateToEditProfileScreen()

    suspend fun navigateToChangePasswordScreen()

    suspend fun navigateToAuthenticationScreen()
}
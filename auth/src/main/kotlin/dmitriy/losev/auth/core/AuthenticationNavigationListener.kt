package dmitriy.losev.auth.core

import dmitriy.losev.firebase.domain.models.UserDescription

interface AuthenticationNavigationListener {

    suspend fun navigateToLoginScreen()

    suspend fun navigateToDataScreen()

    suspend fun navigateToPasswordScreen(userDescription: UserDescription)

    suspend fun navigateToPasswordResetScreen(email: String?)

   suspend fun navigateToProfileScreen()
}
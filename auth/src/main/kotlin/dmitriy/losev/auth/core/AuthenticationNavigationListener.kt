package dmitriy.losev.auth.core

interface AuthenticationNavigationListener {

    suspend fun navigateToLoginScreen()

    suspend fun navigateToRegistrationScreen()

    suspend fun navigateToPasswordScreen(firstName: String?, lastName: String?, patronymic: String?, email: String)

    suspend fun navigateToPasswordResetScreen(email: String?)

    suspend fun navigateToProfileScreen()

    suspend fun back()
}
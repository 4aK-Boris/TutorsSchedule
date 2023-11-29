package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase

class NavigationListenerUseCases(
    errorHandler: ErrorHandler,
    private val navigationUseCases: NavigationUseCases,
    private val navigationProfileUseCases: NavigationProfileUseCases,
    private val navigationStudentsUseCases: NavigationStudentsUseCases,
    private val navigationAuthenticationUseCases: NavigationAuthenticationUseCases
) : AppBaseUseCase(errorHandler = errorHandler) {

    fun getProfileNavigationListener(
        navController: NavController
    ): ProfileNavigationListener {

        return object : ProfileNavigationListener {

            override suspend fun navigateToProfileScreen() {
                navigationProfileUseCases.navigateToProfileScreen(navController)
            }

            override suspend fun navigateToEditProfileScreen() {
                navigationProfileUseCases.navigateToEditProfileScreen(navController)
            }

            override suspend fun navigateToChangePasswordScreen() {
                navigationProfileUseCases.navigateToChangePasswordScreen(navController)
            }

            override suspend fun navigateToAuthenticationScreen() {
                navigationUseCases.navigateToAuthenticationsScreen(navController)
            }
        }
    }

    fun getAuthenticationNavigationListener(navController: NavController): AuthenticationNavigationListener {

        return object : AuthenticationNavigationListener {

            override suspend fun navigateToDataScreen() {
                navigationAuthenticationUseCases.navigateToDataScreen(navController)
            }

            override suspend fun navigateToLoginScreen() {
               navigationAuthenticationUseCases.navigateToLoginScreen(navController)
            }

            override suspend fun navigateToPasswordResetScreen(email: String?) {
                navigationAuthenticationUseCases.navigateToPasswordResetScreen(navController, email)
            }

            override suspend fun navigateToPasswordScreen(userDescription: UserDescription) {
                navigationAuthenticationUseCases.navigateToPasswordScreen(navController, userDescription)
            }

            override suspend fun navigateToProfileScreen() {
                navigationUseCases.navigateProfileScreen(navController)
            }
        }
    }

    fun getStudentsNavigationListener(navController: NavController): StudentsNavigationListener {

        return object : StudentsNavigationListener {

            override suspend fun navigateToStudentsScreen() {
                navigationStudentsUseCases.navigateToStudentsScreen(navController)
            }

            override suspend fun navigateToUpdateStudentScreen(studentId: String) {
                navigationStudentsUseCases.navigateToUpdateStudentScreen(navController, studentId)
            }

            override suspend fun navigateToAddStudentScreen() {
                navigationStudentsUseCases.navigateToAddStudentScreen(navController)
            }

            override suspend fun navigateToAuthenticationScreen() {
                navigationUseCases.navigateToAuthenticationsScreen(navController)
            }
        }
    }
}
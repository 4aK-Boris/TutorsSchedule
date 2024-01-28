package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.navigation.ProfileScreens
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase
import dmitriy.losev.tutorsschedule.core.MainNavigationListener

class NavigationListenerUseCases(
    private val navigationMainUseCases: NavigationMainUseCases,
    private val navigationProfileUseCases: NavigationProfileUseCases,
    private val navigationStudentsUseCases: NavigationStudentsUseCases,
    private val navigationAuthenticationUseCases: NavigationAuthenticationUseCases,
    private val navigationSubjectsUseCases: NavigationSubjectsUseCases,
    private val navigationUseCases: NavigationUseCases
) : AppBaseUseCase() {

    fun getProfileNavigationListener(
        navController: NavController
    ): ProfileNavigationListener {

        return object : ProfileNavigationListener {

            override suspend fun navigateToProfileScreen() {
                navigationProfileUseCases.navigateToProfileScreen(navController)
            }

            override suspend fun navigateToEditProfileScreen(uri: String?) {
                navigationProfileUseCases.navigateToEditProfileScreen(navController, uri)
                navigationUseCases.removeScreen(navController, route = ProfileScreens.CameraScreen.route)
            }

            override suspend fun navigateToChangePasswordScreen() {
                navigationProfileUseCases.navigateToChangePasswordScreen(navController)
            }

            override suspend fun navigateToChangeEmailScreen() {
                navigationProfileUseCases.navigateToChangeEmailScreen(navController)
            }

            override suspend fun navigateToSettingsScreen() {
                navigationProfileUseCases.navigateToSettingsScreen(navController)
            }

            override suspend fun navigateToCameraScreen() {
                navigationProfileUseCases.navigateToCameraScreen(navController)
            }

            override suspend fun navigateToAuthenticationScreen() {
                navigationUseCases.clearScreen(navController, count = 50)
                navigationMainUseCases.navigateToAuthenticationsScreen(navController)
            }

            override suspend fun navigationToSubjectsScreen() {
                navigationSubjectsUseCases.navigateToSubjectsScreen(navController)
            }

            override suspend fun back() {
                navigationUseCases.back(navController)
            }
        }
    }

    fun getAuthenticationNavigationListener(navController: NavController): AuthenticationNavigationListener {

        return object : AuthenticationNavigationListener {

            override suspend fun navigateToRegistrationScreen() {
                navigationAuthenticationUseCases.navigateToRegistrationScreen(navController)
            }

            override suspend fun navigateToLoginScreen() {
               navigationAuthenticationUseCases.navigateToLoginScreen(navController)
            }

            override suspend fun navigateToPasswordResetScreen(email: String?) {
                navigationAuthenticationUseCases.navigateToPasswordResetScreen(navController, email)
            }

            override suspend fun navigateToPasswordScreen(firstName: String?, lastName: String?, patronymic: String?, email: String) {
                navigationAuthenticationUseCases.navigateToPasswordScreen(navController, firstName, lastName, patronymic, email)
            }

            override suspend fun navigateToProfileScreen() {
                navigationUseCases.clearScreen(navController, count = 10)
                navigationMainUseCases.navigateProfileScreen(navController)
            }

            override suspend fun back() {
                navigationUseCases.back(navController)
            }
        }
    }

    fun getSubjectsNavigationListener(navController: NavController): SubjectsNavigationListener {

        return object : SubjectsNavigationListener {

            override suspend fun navigateToAddSubjectScreen() {
                navigationSubjectsUseCases.navigateToAddSubjectScreen(navController)
            }

            override suspend fun navigateToEditSubjectScreen(subjectId: String) {
                navigationSubjectsUseCases.navigateToEditSubjectScreen(navController, subjectId)
            }

            override suspend fun navigateToProfileScreen() {
                navigationProfileUseCases.navigateToProfileScreen(navController)
            }

            override suspend fun back() {
                navigationUseCases.back(navController)
            }
        }
    }

    fun getStudentsNavigationListener(navController: NavController): StudentsNavigationListener {

        return object : StudentsNavigationListener {

            override suspend fun navigateToStudentsScreen() {
                navigationStudentsUseCases.navigateToStudentsScreen(navController)
            }

            override suspend fun navigateToStudentScreen(studentId: String) {
                navigationStudentsUseCases.navigateToStudentScreen(navController, studentId)
            }

            override suspend fun navigateToUpdateStudentScreen(studentId: String) {
                navigationStudentsUseCases.navigateToUpdateStudentScreen(navController, studentId)
            }

            override suspend fun navigateToAddStudentScreen() {
                navigationStudentsUseCases.navigateToAddStudentScreen(navController)
            }

            override suspend fun navigateToAuthenticationScreen() {
                navigationMainUseCases.navigateToAuthenticationsScreen(navController)
            }

            override suspend fun navigateToContactScreen(studentId: String, contactId: String) {
                navigationStudentsUseCases.navigateToContactScreen(navController, studentId, contactId)
            }

            override suspend fun navigateToAddContactScreen(studentId: String) {
                navigationStudentsUseCases.navigateToAddContactScreen(navController, studentId)
            }

            override suspend fun navigateToUpdateContactScreen(studentId: String, contactId: String) {
                navigationStudentsUseCases.navigateToUpdateContactScreen(navController, studentId, contactId)
            }

            override suspend fun back() {
                navigationUseCases.back(navController)
            }
        }
    }

    fun getMainNavigationListener(navController: NavController): MainNavigationListener {

        return object : MainNavigationListener {

            override suspend fun navigateToProfileScreen() {
                navigationMainUseCases.navigateProfileScreen(navController)
            }

            override suspend fun navigateToStudentsScreen() {
                navigationMainUseCases.navigateToStudentsScreen(navController)
            }
        }
    }
}
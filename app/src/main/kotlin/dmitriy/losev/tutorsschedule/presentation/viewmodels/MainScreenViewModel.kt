package dmitriy.losev.tutorsschedule.presentation.viewmodels

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnMain
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationListenerUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationUseCases
import dmitriy.losev.tutorsschedule.presentation.ui.drawer.DrawerScreens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(
    errorHandler: ErrorHandler,
    private val navigationUseCases: NavigationUseCases,
    private val navigationListenerUseCases: NavigationListenerUseCases,
    private val profileNavigationUseCases: ProfileNavigationUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _screen = MutableStateFlow(value = DrawerScreens.CALENDER_SCREEN)

    val screen = _screen.asStateFlow()

    fun changeScreen(screen: DrawerScreens, navController: NavController) {
        _screen.value = screen
        navigateToDestination(navController, screen.route)
    }

    fun navigateToDestination(navController: NavController, route: String) = runOnMain {
        navigationUseCases.navigateToDestination(navController, route)
    }

    fun getProfileNavigationListener(navController: NavController): ProfileNavigationListener {
        return navigationListenerUseCases.getProfileNavigationListener(navController)
    }

    fun getAuthenticationNavigationListener(navController: NavController): AuthenticationNavigationListener {
        return navigationListenerUseCases.getAuthenticationNavigationListener(navController)
    }

    fun getStudentsNavigationListener(navController: NavController): StudentsNavigationListener {
        return navigationListenerUseCases.getStudentsNavigationListener(navController)
    }
}
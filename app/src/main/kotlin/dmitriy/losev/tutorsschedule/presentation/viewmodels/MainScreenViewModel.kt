package dmitriy.losev.tutorsschedule.presentation.viewmodels

import androidx.navigation.NavController
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.navigation.ProfileScreens
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.navigation.StudentsScreens
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.tutorsschedule.core.MainNavigationListener
import dmitriy.losev.tutorsschedule.domain.usecases.MainNavigationUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationListenerUseCases
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.menu.MenuItem
import dmitriy.losev.ui.views.menu.MenuStateHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(
    private val menuStateHandler: MenuStateHandler,
    private val navigationListenerUseCases: NavigationListenerUseCases,
    private val mainNavigationUseCases: MainNavigationUseCases
) : BaseViewModel() {

    private val _selectedMenuItem = MutableStateFlow(value = MenuItem.PROFILE)

    val selectedMenuItem = _selectedMenuItem.asStateFlow()

    val menuState = menuStateHandler.menuState

    fun onSelectedMenuItemChanged(menuItem: MenuItem, mainNavigationListener: MainNavigationListener) = runOnMain {
        _selectedMenuItem.value = menuItem
        when(menuItem) {
            MenuItem.PROFILE -> mainNavigationUseCases.navigateToProfileScreen(mainNavigationListener)
            MenuItem.STUDENTS -> mainNavigationUseCases.navigateToStudentsScreen(mainNavigationListener)
            else -> {}
        }
    }

//    fun loadThemeState() = runOnBackground {
//        safeCall { settingsGetThemeStateUseCase.getThemeState() }.processing { themeFlow ->
//            themeState = themeFlow
//        }
//    }


//    fun changeScreen(screen: DrawerScreens, navController: NavController) {
//        _screen.value = screen
//        navigateToDestination(navController, screen.route)
//    }

//    fun navigateToDestination(navController: NavController, route: String) = runOnMain {
//        navigationUseCases.navigateToDestination(navController, route)
//    }

    fun getProfileNavigationListener(navController: NavController): ProfileNavigationListener {
        return navigationListenerUseCases.getProfileNavigationListener(navController)
    }

    fun getAuthenticationNavigationListener(navController: NavController): AuthenticationNavigationListener {
        return navigationListenerUseCases.getAuthenticationNavigationListener(navController)
    }

    fun getSubjectsNavigationListener(navController: NavController): SubjectsNavigationListener{
        return navigationListenerUseCases.getSubjectsNavigationListener(navController)
    }

    fun getStudentsNavigationListener(navController: NavController): StudentsNavigationListener {
        return navigationListenerUseCases.getStudentsNavigationListener(navController)
    }

    fun getMainNavigationListener(navController: NavController): MainNavigationListener {
        return navigationListenerUseCases.getMainNavigationListener(navController)
    }

    fun getOnDestinationChangedListener(): NavController.OnDestinationChangedListener {
        return NavController.OnDestinationChangedListener { _, destination, _ ->
            menuStateHandler.onMenuStateChanged(menuState = destination.route in menuVisibleScreens)
        }
    }

    companion object {

        private val menuVisibleScreens = setOf(
            ProfileScreens.ProfileScreen.route,
            StudentsScreens.StudentsScreen.route
        )
    }
}
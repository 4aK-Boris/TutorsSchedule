package dmitriy.losev.tutorsschedule.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.tutorsschedule.presentation.navigation.Navigation
import dmitriy.losev.tutorsschedule.presentation.viewmodels.MainScreenViewModel
import dmitriy.losev.ui.views.menu.Menu
import dmitriy.losev.ui.views.menu.MenuItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    client: SignInClient,
    viewModel: MainScreenViewModel = koinViewModel()
) {

    val navController = rememberNavController()

    val onDestinationChangedListener = viewModel.getOnDestinationChangedListener()

    navController.addOnDestinationChangedListener(onDestinationChangedListener)

    //val themeState by viewModel.themeState.collectAsState(initial = THEME_DEFAULT_VALUE)

    val menuState by viewModel.menuState.collectAsState()
    val selectedMenuItem by viewModel.selectedMenuItem.collectAsState()

    val profileNavigationListener = viewModel.getProfileNavigationListener(navController)
    val authenticationNavigationListener = viewModel.getAuthenticationNavigationListener(navController)
    val subjectsNavigationListener = viewModel.getSubjectsNavigationListener(navController)
    val studentsNavigationListener = viewModel.getStudentsNavigationListener(navController)
    val mainNavigationListener = viewModel.getMainNavigationListener(navController)

    LaunchedEffect(key1 = Unit) {
        viewModel.loadThemeState()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (menuState) {
                Menu(
                    selectedItem = selectedMenuItem,
                    onProfileMenuItemClicked = { viewModel.onSelectedMenuItemChanged(menuItem = MenuItem.PROFILE, mainNavigationListener) },
                    onCalendarMenuItemClicked = { viewModel.onSelectedMenuItemChanged(menuItem = MenuItem.CALENDAR, mainNavigationListener) },
                    onFinanceMenuItemClicked = { viewModel.onSelectedMenuItemChanged(menuItem = MenuItem.FINANCE, mainNavigationListener) },
                    onStudentsMenuItemClicked = { viewModel.onSelectedMenuItemChanged(menuItem = MenuItem.STUDENTS, mainNavigationListener) }
                )
            }
        }
    ) { paddingValues ->
        Navigation(
            modifier = Modifier.padding(paddingValues = paddingValues),
            client = client,
            authenticationNavigationListener = authenticationNavigationListener,
            profileNavigationListener = profileNavigationListener,
            studentsNavigationListener = studentsNavigationListener,
            subjectsNavigationListener = subjectsNavigationListener,
            navController = navController
        )
    }
}
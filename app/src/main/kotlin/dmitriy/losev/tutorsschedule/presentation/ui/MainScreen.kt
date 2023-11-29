package dmitriy.losev.tutorsschedule.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.tutorsschedule.presentation.navigation.Navigation
import dmitriy.losev.tutorsschedule.presentation.ui.drawer.Drawer
import dmitriy.losev.tutorsschedule.presentation.viewmodels.MainScreenViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    client: SignInClient,
    viewModel: MainScreenViewModel = koinNavViewModel()
) {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val profileNavigationListener = viewModel.getProfileNavigationListener(navController)
    val authenticationNavigationListener = viewModel.getAuthenticationNavigationListener(navController)
    val studentsNavigationListener = viewModel.getStudentsNavigationListener(navController)

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier.fillMaxSize(),
        drawerContent = {
            Drawer(viewModel = viewModel, navController = navController) {
                scope.launch {
                    drawerState.close()
                }
            }
        }
    ) {
        Navigation(
            client = client,
            profileNavigationListener = profileNavigationListener,
            studentsNavigationListener = studentsNavigationListener,
            authenticationNavigationListener = authenticationNavigationListener,
            navController = navController
        ) {
            scope.launch {
                drawerState.open()
            }
        }
    }
}
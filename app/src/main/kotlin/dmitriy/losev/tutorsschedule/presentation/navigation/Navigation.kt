package dmitriy.losev.tutorsschedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.navigation.authenticationNavigation
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.navigation.profileNavigation
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.navigation.studentsNavigation
import dmitriy.losev.tutorsschedule.presentation.ui.screens.CalendarScreen
import dmitriy.losev.tutorsschedule.presentation.ui.screens.FinanceScreen


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileNavigationListener: ProfileNavigationListener,
    studentsNavigationListener: StudentsNavigationListener,
    authenticationNavigationListener: AuthenticationNavigationListener,
    client: SignInClient,
    startDestination: String = Screens.CalendarScreen.route,
    openDrawer: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        authenticationNavigation(
            route = Screens.AuthenticationScreen.route,
            client = client,
            authenticationNavigationListener = authenticationNavigationListener
        )

        composable(
            route = Screens.CalendarScreen.route
        ) {
           CalendarScreen(openDrawer)
        }

        composable(
            route = Screens.FinanceScreen.route
        ) {
            FinanceScreen()
        }

        profileNavigation(
            profileNavigationListener = profileNavigationListener,
            route = Screens.ProfileScreen.route,
            openDrawer = openDrawer
        )

        studentsNavigation(
            studentsNavigationListener = studentsNavigationListener,
            route = Screens.StudentsScreen.route,
            openDrawer = openDrawer
        )
    }
}

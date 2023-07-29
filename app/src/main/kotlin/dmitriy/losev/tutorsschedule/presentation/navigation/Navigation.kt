package dmitriy.losev.tutorsschedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.presentation.navigation.authenticationNavigation
import dmitriy.losev.tutorsschedule.presentation.ui.screens.EmptyScreen


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    client: SignInClient,
    startDestination: String = Screens.EmptyScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        authenticationNavigation(
            navController = navController,
            route = Screens.AuthenticationScreen.route,
            client = client,
        )

        composable(
            route = Screens.EmptyScreen.route
        ) {
            EmptyScreen(client = client, navController = navController)
        }
    }
}

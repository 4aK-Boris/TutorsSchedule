package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.StartScreenViewModel
import dmitriy.losev.ui.views.Logo
import dmitriy.losev.ui.views.boxes.ColumnPrimary
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.buttons.SecondaryButton
import org.koin.androidx.compose.koinViewModel


@Composable
fun StartScreen(
    authenticationNavigationListener: AuthenticationNavigationListener,
    viewModel: StartScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.checkAuth(authenticationNavigationListener)
    }

    ColumnPrimary(verticalArrangement = Arrangement.SpaceBetween) {

        Logo(modifier = Modifier.padding(top = 136.dp))

        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

            PrimaryButton(text = stringResource(id = R.string.entrance)) {
                viewModel.navigateToLoginScreen(authenticationNavigationListener)
            }

            Spacer(modifier = Modifier.height(height = 24.dp))

            SecondaryButton(text = stringResource(id = R.string.registration)) {
                viewModel.navigateToRegistrationScreen(authenticationNavigationListener)
            }

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}



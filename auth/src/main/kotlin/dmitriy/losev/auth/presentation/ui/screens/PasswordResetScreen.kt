package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.auth.presentation.viewmodels.PasswordResetScreenViewModel
import dmitriy.losev.ui.views.LogoWithBackButton
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnPrimaryWithLoader
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.textfields.EmailTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordResetScreen(
    authenticationNavigationListener: AuthenticationNavigationListener,
    inputEmail: String?,
    viewModel: PasswordResetScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = inputEmail) {
        viewModel.onEmailChanged(email = inputEmail ?: EMPTY_STRING)
    }

    val email by viewModel.email.collectAsState()

    val emailTextFieldState by viewModel.emailTextFieldState.collectAsState()

    ColumnPrimaryWithLoader(verticalArrangement = Arrangement.SpaceBetween, viewModel = viewModel) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoWithBackButton {
                viewModel.back(authenticationNavigationListener)
            }

            Title1Text(text = stringResource(id = R.string.password_recovery))

            EmailTextField(
                title = stringResource(id = R.string.enter_email),
                email = email,
                onEmailChanged = viewModel::onEmailChanged,
                textFieldState = emailTextFieldState,
                clearTextFieldState = viewModel::clearEmailTextFieldState
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VerticalSpacer(height = 40.dp)

            PrimaryButton(text = stringResource(id = R.string.further)) {
                viewModel.resetPassword(authenticationNavigationListener)
            }

            VerticalSpacer(height = 120.dp)
        }
    }
}
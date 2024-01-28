package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.PasswordScreenViewModel
import dmitriy.losev.ui.views.LogoWithBackButton
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnPrimaryWithLoader
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.textfields.PasswordTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordScreen(
    firstName: String?,
    lastName: String?,
    patronymic: String?,
    email: String,
    authenticationNavigationListener: AuthenticationNavigationListener,
    viewModel: PasswordScreenViewModel = koinViewModel()
) {

    val password1 by viewModel.password1.collectAsState()
    val password2 by viewModel.password2.collectAsState()

    val password1TextFieldState by viewModel.password1TextFieldState.collectAsState()
    val password2TextFieldState by viewModel.password2TextFieldState.collectAsState()

    val password1Visible by viewModel.password1Visible.collectAsState()
    val password2Visible by viewModel.password2Visible.collectAsState()

    ColumnPrimaryWithLoader(verticalArrangement = Arrangement.SpaceBetween, viewModel = viewModel) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoWithBackButton {
                viewModel.back(authenticationNavigationListener)
            }

            Title1Text(text = stringResource(id = R.string.enter_password))

            VerticalSpacer(height = 56.dp)

            PasswordTextField(
                title = stringResource(id = R.string.password),
                hint = stringResource(id = R.string.password),
                password = password1,
                onPasswordChanged = viewModel::onPassword1Changed,
                imeAction = ImeAction.Next,
                passwordVisible = password1Visible,
                onPasswordVisibleChanged = viewModel::onPassword1VisibleChange,
                textFieldState = password1TextFieldState,
                clearTextFieldState = viewModel::clearPassword1TextFieldState
            )

            VerticalSpacer(height = 24.dp)

            PasswordTextField(
                title = stringResource(id = R.string.confirm_password),
                hint = stringResource(id = R.string.password),
                password = password2,
                onPasswordChanged = viewModel::onPassword2Changed,
                passwordVisible = password2Visible,
                onPasswordVisibleChanged = viewModel::onPassword2VisibleChange,
                textFieldState = password2TextFieldState,
                clearTextFieldState = viewModel::clearPassword2TextFieldState
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VerticalSpacer(height = 40.dp)

            PrimaryButton(text = stringResource(id = R.string.registration)) {
                viewModel.registration(authenticationNavigationListener, firstName, lastName, patronymic, email)
            }

            VerticalSpacer(height = 120.dp)
        }
    }
}
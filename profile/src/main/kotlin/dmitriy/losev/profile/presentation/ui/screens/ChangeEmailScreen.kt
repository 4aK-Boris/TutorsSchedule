package dmitriy.losev.profile.presentation.ui.screens

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
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.viewmodels.ChangeEmailScreenViewModel
import dmitriy.losev.ui.views.LogoWithBackButton
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.textfields.EmailTextField
import dmitriy.losev.ui.views.textfields.PasswordTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangeEmailScreen(
    profileNavigationListener: ProfileNavigationListener,
    viewModel: ChangeEmailScreenViewModel = koinViewModel()
) {

    val oldEmail by viewModel.oldEmail.collectAsState()
    val newEmail by viewModel.newEmail.collectAsState()
    val password by viewModel.password.collectAsState()

    val oldEmailTextFieldState by viewModel.oldEmailTextFieldState.collectAsState()
    val newEmailTextFieldState by viewModel.newEmailTextFieldState.collectAsState()
    val passwordTextFieldState by viewModel.passwordTextFieldState.collectAsState()

    val passwordVisible by viewModel.passwordVisible.collectAsState()

    ColumnSecondaryWithLoader(verticalArrangement = Arrangement.SpaceBetween, viewModel = viewModel) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoWithBackButton {
                viewModel.back(profileNavigationListener)
            }

            Title2Text(text = stringResource(id = R.string.change_email))

            VerticalSpacer(height = 40.dp)

            EmailTextField(
                title = stringResource(id = R.string.old_email),
                email = oldEmail,
                onEmailChanged = viewModel::onOldEmailChanged,
                imeAction = ImeAction.Next,
                textFieldState = oldEmailTextFieldState,
                clearTextFieldState = viewModel::clearOldEmailTextFieldState
            )

            VerticalSpacer(height = 40.dp)

            EmailTextField(
                title = stringResource(id = R.string.new_email),
                email = newEmail,
                onEmailChanged = viewModel::onNewEmailChanged,
                imeAction = ImeAction.Next,
                textFieldState = newEmailTextFieldState,
                clearTextFieldState = viewModel::clearNewEmailTextFieldState
            )

            VerticalSpacer(height = 40.dp)

            PasswordTextField(
                title = stringResource(id = R.string.confirm_password),
                hint = stringResource(id = R.string.password),
                password = password,
                onPasswordChanged = viewModel::onPasswordChanged,
                passwordVisible = passwordVisible,
                onPasswordVisibleChanged = viewModel::onPasswordVisibleChanged,
                textFieldState = passwordTextFieldState,
                clearTextFieldState = viewModel::clearPasswordTextFieldState
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 40.dp)

            PrimaryButton(text = stringResource(id = R.string.save)) {
                viewModel.changeEmail(profileNavigationListener)
            }

            VerticalSpacer(height = 120.dp)
        }
    }
}


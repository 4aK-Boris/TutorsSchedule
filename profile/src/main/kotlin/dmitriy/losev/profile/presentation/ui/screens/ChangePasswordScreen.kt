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
import dmitriy.losev.profile.presentation.viewmodels.ChangePasswordScreenViewModel
import dmitriy.losev.ui.views.LogoWithBackButton
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.textfields.PasswordTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangePasswordScreen(
    profileNavigationListener: ProfileNavigationListener,
    viewModel: ChangePasswordScreenViewModel = koinViewModel()
) {

    val oldPassword by viewModel.oldPassword.collectAsState()
    val newPassword1 by viewModel.newPassword1.collectAsState()
    val newPassword2 by viewModel.newPassword2.collectAsState()

    val oldPasswordTextFieldState by viewModel.oldPasswordTextFieldState.collectAsState()
    val newPassword1TextFieldState by viewModel.newPassword1TextFieldState.collectAsState()
    val newPassword2TextFieldState by viewModel.newPassword2TextFieldState.collectAsState()

    val oldPasswordVisible by viewModel.oldPasswordVisible.collectAsState()
    val newPassword1Visible by viewModel.newPassword1Visible.collectAsState()
    val newPassword2Visible by viewModel.newPassword2Visible.collectAsState()

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

            PasswordTextField(
                title = stringResource(id = R.string.current_password),
                hint = stringResource(id = R.string.password),
                password = oldPassword,
                imeAction = ImeAction.Next,
                onPasswordChanged = viewModel::onOldPasswordChanged,
                passwordVisible = oldPasswordVisible,
                onPasswordVisibleChanged = viewModel::onOldPasswordVisibleChanged,
                textFieldState = oldPasswordTextFieldState,
                clearTextFieldState = viewModel::clearOldPasswordTextFieldState
            )

            VerticalSpacer(height = 40.dp)

            PasswordTextField(
                title = stringResource(id = R.string.new_password),
                hint = stringResource(id = R.string.password),
                password = newPassword1,
                imeAction = ImeAction.Next,
                onPasswordChanged = viewModel::onNewPassword1Changed,
                passwordVisible = newPassword1Visible,
                onPasswordVisibleChanged = viewModel::onNewPassword1VisibleChanged,
                textFieldState = newPassword1TextFieldState,
                clearTextFieldState = viewModel::clearNewPassword1TextFieldState
            )

            VerticalSpacer(height = 40.dp)

            PasswordTextField(
                title = stringResource(id = R.string.confirm_new_password),
                hint = stringResource(id = R.string.password),
                password = newPassword2,
                onPasswordChanged = viewModel::onNewPassword2Changed,
                passwordVisible = newPassword2Visible,
                onPasswordVisibleChanged = viewModel::onNewPassword2VisibleChanged,
                textFieldState = newPassword2TextFieldState,
                clearTextFieldState = viewModel::clearNewPassword2TextFieldState
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 40.dp)

            PrimaryButton(text = stringResource(id = R.string.save)) {
                viewModel.changePassword(profileNavigationListener)
            }

            VerticalSpacer(height = 120.dp)
        }
    }
}


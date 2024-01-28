package dmitriy.losev.auth.presentation.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.LoginScreenViewModel
import dmitriy.losev.ui.views.HintText
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.LogoWithBackButton
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnPrimaryWithLoader
import dmitriy.losev.ui.views.buttons.AuthenticationIconButton
import dmitriy.losev.ui.views.buttons.HintButton
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.textfields.EmailTextField
import dmitriy.losev.ui.views.textfields.PasswordTextField
import dmitriy.losev.vk.presentation.ui.VKAuthenticationButton
import dmitriy.losev.yandex.presentation.ui.YandexAuthenticationButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(authenticationNavigationListener: AuthenticationNavigationListener, client: SignInClient, viewModel: LoginScreenViewModel = koinViewModel()) {

    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        viewModel.authWithGoogleIntent(authenticationNavigationListener, result, client)
    }

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    val emailTextFieldState by viewModel.emailTextFieldState.collectAsState()
    val passwordTextFieldState by viewModel.passwordTextFieldState.collectAsState()

    val passwordVisible by viewModel.passwordVisible.collectAsState()

    val authenticationListener = viewModel.getAuthenticationListener(authenticationNavigationListener)

    ColumnPrimaryWithLoader(verticalArrangement = Arrangement.SpaceBetween, viewModel = viewModel) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoWithBackButton {
                viewModel.back(authenticationNavigationListener)
            }

            VerticalSpacer(height = 3.dp)

            EmailTextField(
                title = stringResource(id = R.string.email),
                email = email,
                onEmailChanged = viewModel::onEmailChanged,
                imeAction = ImeAction.Next,
                textFieldState = emailTextFieldState,
                clearTextFieldState = viewModel::clearEmailTextFieldState
            )

            VerticalSpacer(height = 40.dp)

            PasswordTextField(
                title = stringResource(id = R.string.enter_password),
                hint = stringResource(id = R.string.password),
                password = password,
                onPasswordChanged = viewModel::onPasswordChanged,
                passwordVisible = passwordVisible,
                onPasswordVisibleChanged = viewModel::onPasswordVisibleChanged,
                textFieldState = passwordTextFieldState,
                clearTextFieldState = viewModel::clearPasswordTextFieldState
            )

            VerticalSpacer(height = 40.dp)

            HintText(text = stringResource(id = R.string.log_in_using))

            VerticalSpacer(height = 24.dp)

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

                YandexAuthenticationButton(authenticationListener = authenticationListener)

                HorizontalSpacer(width = 24.dp)

                AuthenticationIconButton(icon = painterResource(id = R.drawable.google), contentDescription = stringResource(id = R.string.google_login)) {
                    viewModel.authWithGoogle(client, googleLauncher)
                }

                HorizontalSpacer(width = 24.dp)

                VKAuthenticationButton(authenticationListener = authenticationListener)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VerticalSpacer(height = 40.dp)

            PrimaryButton(text = stringResource(id = R.string.entrance)) {
                viewModel.authWithEmail(authenticationNavigationListener)
            }

            VerticalSpacer(height = 16.dp)

            HintButton(text = stringResource(id = R.string.forgot_password)) {
                viewModel.navigateToPasswordResetScreen(authenticationNavigationListener)
            }

            VerticalSpacer(height = 80.dp)
        }
    }
}
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.RegistrationScreenViewModel
import dmitriy.losev.ui.views.LogoWithBackButton
import dmitriy.losev.ui.views.Title1Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnPrimaryWithLoader
import dmitriy.losev.ui.views.buttons.PrimaryButton
import dmitriy.losev.ui.views.textfields.DefaultTextField
import dmitriy.losev.ui.views.textfields.EmailTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(authenticationNavigationListener: AuthenticationNavigationListener, viewModel: RegistrationScreenViewModel = koinViewModel()) {
    
    val email by viewModel.email.collectAsState()
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val patronymic by viewModel.patronymic.collectAsState()

    val emailTExtFieldState by viewModel.emailTextFieldState.collectAsState()

    ColumnPrimaryWithLoader(verticalArrangement = Arrangement.SpaceBetween, viewModel = viewModel) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoWithBackButton {
                viewModel.back(authenticationNavigationListener)
            }

            Title1Text(text = stringResource(id = R.string.register))

            VerticalSpacer(height = 56.dp)

            DefaultTextField(
                title = stringResource(id = R.string.first_name),
                hint = stringResource(id = R.string.first_name),
                text = firstName,
                imeAction = ImeAction.Next,
                onTextChanged = viewModel::onFirstNameChanged,
                capitalization = KeyboardCapitalization.Words
            )

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.last_name),
                hint = stringResource(id = R.string.last_name),
                text = lastName,
                imeAction = ImeAction.Next,
                onTextChanged = viewModel::onLastNameChanged,
                capitalization = KeyboardCapitalization.Words
            )

            VerticalSpacer(height = 24.dp)


            DefaultTextField(
                title = stringResource(id = R.string.patronymic),
                hint = stringResource(id = R.string.patronymic),
                text = patronymic,
                imeAction = ImeAction.Next,
                onTextChanged = viewModel::onPatronymicChanged,
                capitalization = KeyboardCapitalization.Words
            )

            VerticalSpacer(height = 32.dp)

            EmailTextField(
                title = stringResource(id = R.string.email),
                email = email,
                onEmailChanged = viewModel::onEmailChanged,
                textFieldState = emailTExtFieldState,
                clearTextFieldState = viewModel::clearEmailTextFieldState
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VerticalSpacer(height = 40.dp)

            PrimaryButton(text = stringResource(id = R.string.further)) {
                viewModel.onNextButtonClick(authenticationNavigationListener)
            }

            VerticalSpacer(height = 40.dp)
        }
    }
}

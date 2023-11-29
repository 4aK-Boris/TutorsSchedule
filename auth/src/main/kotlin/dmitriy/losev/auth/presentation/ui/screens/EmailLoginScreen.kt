package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.EmailLoginScreenViewModel
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.core.presentation.ui.views.DefaultPasswordTextField
import dmitriy.losev.core.presentation.ui.views.DefaultTextField
import dmitriy.losev.core.theme.TutorsScheduleTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmailLoginScreen(authenticationNavigationListener: AuthenticationNavigationListener, viewModel: EmailLoginScreenViewModel = koinViewModel()) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .tutorsScheduleBackground(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 32.dp))

        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = stringResource(id = R.string.main_image),
            modifier = Modifier.size(size = 256.dp)
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        DefaultTextField(
            text = email,
            onTextChanged = viewModel::onEmailChanged,
            placeHolder = stringResource(id = R.string.email),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        Column(
            modifier = Modifier.wrapContentWidth(align = Alignment.End),
            horizontalAlignment = Alignment.End
        ) {

            DefaultPasswordTextField(
                password = password,
                onPasswordChanged = viewModel::onPasswordChanged,
                placeHolder = stringResource(id = R.string.password)
            )

            Text(
                text = stringResource(id = R.string.forgot_password),
                style = TutorsScheduleTheme.typography.hints,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clickable { viewModel.navigateToPasswordResetScreen(authenticationNavigationListener) }
            )
        }

        Spacer(modifier = Modifier.height(height = 24.dp))

        DefaultButton(text = stringResource(id = R.string.log_in)) {
            viewModel.authWithEmail(authenticationNavigationListener)
        }
    }
}
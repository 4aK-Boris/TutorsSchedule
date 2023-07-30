package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitriy.losev.auth.R
import dmitriy.losev.auth.presentation.ui.views.AuthenticationButton
import dmitriy.losev.auth.presentation.ui.views.AuthenticationTextField
import dmitriy.losev.auth.presentation.viewmodels.EmailLoginScreenViewModel
import dmitriy.losev.core.theme.TutorsScheduleTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmailLoginScreen(navController: NavController, viewModel: EmailLoginScreenViewModel = koinViewModel()) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        TutorsScheduleTheme.colors.backgroundPrimary,
                        TutorsScheduleTheme.colors.backgroundSecondary,
                    )
                )
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 64.dp))

        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = stringResource(id = R.string.main_image),
            modifier = Modifier.size(size = 256.dp)
        )

        Spacer(modifier = Modifier.height(height = 64.dp))

        AuthenticationTextField(
            text = email,
            onTextChanged = viewModel::onEmailChanged,
            placeHolder = stringResource(id = R.string.email)
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        AuthenticationTextField(
            text = password,
            onTextChanged = viewModel::onPasswordChanged,
            placeHolder = stringResource(id = R.string.password)
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        AuthenticationButton(text = "Забыли пароль?") {
            viewModel.navigateToPasswordResetScreen(navController = navController)
        }

        Spacer(modifier = Modifier.height(height = 24.dp))

        AuthenticationButton(text = "Авторизоваться") {
            viewModel.authWithEmail(navController = navController)
        }
    }
}
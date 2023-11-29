package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.PasswordResetScreenViewModel
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.core.presentation.ui.views.DefaultTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordResetScreen(
    authenticationNavigationListener: AuthenticationNavigationListener,
    inputEmail: String?,
    viewModel: PasswordResetScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = inputEmail) {
        viewModel.onEmailChanged(email = inputEmail ?: "")
    }

    val email by viewModel.email.collectAsState()

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
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Send,
            onTextChanged = viewModel::onEmailChanged,
            placeHolder = stringResource(id = R.string.email)
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        DefaultButton(text = stringResource(id = R.string.send_message)) {
            viewModel.resetPassword(authenticationNavigationListener)
        }
    }

}
package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dmitriy.losev.auth.presentation.viewmodels.PasswordResetScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordResetScreen(navController: NavController, inputEmail: String, viewModel: PasswordResetScreenViewModel = koinViewModel()) {

    LaunchedEffect(key1 = inputEmail) {
        viewModel.onEmailChanged(email = inputEmail)
    }

    val email by viewModel.email.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "LoginScreen")

        TextField(value = email, onValueChange = viewModel::onEmailChanged)

        Button(onClick = { viewModel.resetPassword(navController = navController) }) {
            Text(text = "Отправить письмо")
        }
    }

}
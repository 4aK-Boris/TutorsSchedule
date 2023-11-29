package dmitriy.losev.auth.presentation.ui.screens

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.auth.R
import dmitriy.losev.auth.presentation.ui.views.AuthenticationTextField
import dmitriy.losev.auth.presentation.viewmodels.DataScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DataScreen(navController: NavController, viewModel: DataScreenViewModel = koinViewModel()) {

    val uri by viewModel.uri.collectAsState()
    val email by viewModel.email.collectAsState()
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()

    val pickImage =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = viewModel::onUriChanged
        )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(height = 64.dp))

        IconButton(
            onClick = {
                pickImage.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            }, modifier = Modifier
                .clip(shape = CircleShape)
                .size(size = 200.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = uri)
                    .crossfade(enable = true)
                    .build(),
                error = painterResource(id = R.drawable.avatar_placeholder),
                contentDescription = stringResource(id = R.string.avatar_content_description),
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.High,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 196.dp),
            )
        }

        Spacer(modifier = Modifier.height(height = 64.dp))


        AuthenticationTextField(
            text = firstName,
            onTextChanged = viewModel::onFirstNameChanged,
            placeHolder = "Имя"
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        AuthenticationTextField(
            text = lastName,
            onTextChanged = viewModel::onLastNameChanged,
            placeHolder = "Фамилия"
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        AuthenticationTextField(
            text = email,
            onTextChanged = viewModel::onEmailChanged,
            placeHolder = "Почта",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        Button(onClick = { viewModel.onNextButtonClick(navController = navController) }) {
            Text(text = "Продолжить")
        }

        Spacer(modifier = Modifier.height(height = 32.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun PreviewDataContent() {

    val viewModel = koinViewModel<DataScreenViewModel>()
    val navController = rememberNavController()

    viewModel.onFirstNameChanged(firstName = "Никита")
    viewModel.onLastNameChanged(lastName = "Архипов")
    viewModel.onEmailChanged(email = "test@gmail.com")

    DataScreen(
        navController = navController,
        viewModel = viewModel
    )
}

@Preview
@Composable
fun PreviewAuthenticationTextField() {

    val (text, onTextChanged) = remember { mutableStateOf(value = "") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AuthenticationTextField(text = text, onTextChanged = onTextChanged, placeHolder = "Имя")
    }
}

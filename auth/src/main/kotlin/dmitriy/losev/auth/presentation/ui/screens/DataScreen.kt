package dmitriy.losev.auth.presentation.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.DataScreenViewModel
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.core.presentation.ui.views.DefaultTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun DataScreen(authenticationNavigationListener: AuthenticationNavigationListener, viewModel: DataScreenViewModel = koinViewModel()) {

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
            .tutorsScheduleBackground(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(height = 32.dp))

        IconButton(
            onClick = { pickImage.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)) },
            modifier = Modifier
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

        Spacer(modifier = Modifier.height(height = 32.dp))

        DefaultTextField(
            text = firstName,
            onTextChanged = viewModel::onFirstNameChanged,
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            placeHolder = stringResource(id = R.string.first_name)
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        DefaultTextField(
            text = lastName,
            onTextChanged = viewModel::onLastNameChanged,
            placeHolder = stringResource(id = R.string.last_name),
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        DefaultTextField(
            text = email,
            onTextChanged = viewModel::onEmailChanged,
            placeHolder = stringResource(id = R.string.email),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        DefaultButton(text = stringResource(id = R.string.resume)) {
            viewModel.onNextButtonClick(authenticationNavigationListener)
        }

        Spacer(modifier = Modifier.height(height = 32.dp))
    }
}

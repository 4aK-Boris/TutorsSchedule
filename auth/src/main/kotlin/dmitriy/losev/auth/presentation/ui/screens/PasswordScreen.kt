package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.viewmodels.PasswordScreenViewModel
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.core.presentation.ui.views.DefaultPasswordTextField
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.firebase.domain.models.UserDescription
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordScreen(
    authenticationNavigationListener: AuthenticationNavigationListener,
    userDescription: UserDescription,
    viewModel: PasswordScreenViewModel = koinViewModel()
) {

    val password1 by viewModel.password1.collectAsState()
    val password2 by viewModel.password2.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
                .tutorsScheduleBackground(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(height = 32.dp))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = userDescription.imageUri)
                    .crossfade(enable = true)
                    .build(),
                fallback = painterResource(id = R.drawable.avatar_placeholder),
                contentDescription = stringResource(id = R.string.avatar_content_description),
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.High,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 196.dp)
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            Text(text = userDescription.email)

            Spacer(modifier = Modifier.height(height = 24.dp))

            DefaultPasswordTextField(
                password = password1,
                onPasswordChanged = viewModel::onPassword1Changed,
                placeHolder = stringResource(id = R.string.enter_password),
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(height = 24.dp))

            DefaultPasswordTextField(
                password = password2,
                onPasswordChanged = viewModel::onPassword2Changed,
                placeHolder = stringResource(id = R.string.repeat_enter_password)
            )

            Spacer(modifier = Modifier.height(height = 24.dp))

            DefaultButton(text = stringResource(id = R.string.register)) {
                viewModel.registration(userDescription, authenticationNavigationListener)
            }
        }

        AnimatedVisibility(visible = isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(size = 32.dp),
                    strokeWidth = 4.dp,
                    trackColor = TutorsScheduleTheme.colors.backgroundPrimary,
                    color = TutorsScheduleTheme.colors.backgroundSecondary
                )
            }

        }
    }
}
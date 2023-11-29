package dmitriy.losev.profile.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.core.presentation.ui.views.DefaultTextView
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.ProfileTopBar
import dmitriy.losev.profile.presentation.viewmodels.ProfileScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    profileNavigationListener: ProfileNavigationListener,
    openDrawer: () -> Unit,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {

    val isAuthenticated by viewModel.isAuthenticated.collectAsState(initial = false)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ProfileTopBar(openDrawer, isAuthenticated) {
                viewModel.navigateToEditProfileScreen(profileNavigationListener)
            }
        }
    ) { paddingValues ->
        ProfileScreenBody(
            modifier = Modifier.padding(paddingValues),
            profileNavigationListener = profileNavigationListener
        )
    }
}

@Composable
fun ProfileScreenBody(
    modifier: Modifier,
    profileNavigationListener: ProfileNavigationListener,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {

    val uri by viewModel.avatarUri.collectAsState()
    val displayName by viewModel.displayName.collectAsState()
    val email by viewModel.email.collectAsState()
    val isAuthenticated by viewModel.isAuthenticated.collectAsState(initial = false)

    val buttonText = if (isAuthenticated) {
        stringResource(id = R.string.exit)
    } else {
        stringResource(id = R.string.enter)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.loadUserData()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .tutorsScheduleBackground(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 32.dp))

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
                .size(size = 200.dp),
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        if (isAuthenticated) {

            DefaultTextView(
                text = displayName ?: stringResource(id = R.string.default_display_name),
                icon = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.first_name_and_last_name)
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            DefaultTextView(
                text = email ?: stringResource(id = R.string.default_email),
                icon = Icons.Default.Email,
                contentDescription = stringResource(id = R.string.email)
            )

        } else  {
            Text(text = "Вы не авторизованы", style = TutorsScheduleTheme.typography.dialogTitle)
        }

        Spacer(modifier = Modifier.height(height = 16.dp))

        DefaultButton(text = buttonText) {
            viewModel.authentication(profileNavigationListener)
        }

        Spacer(modifier = Modifier.height(height = 16.dp))
    }
}
package dmitriy.losev.auth.presentation.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.R
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.presentation.ui.views.AuthenticationGoogleButton
import dmitriy.losev.auth.presentation.viewmodels.StartScreenViewModel
import dmitriy.losev.core.presentation.ui.tutorsScheduleBackground
import dmitriy.losev.core.presentation.ui.views.DefaultButton
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.vk.presentation.ui.VKAuthenticationButton
import dmitriy.losev.yandex.presentation.ui.YandexAuthenticationButton
import org.koin.androidx.compose.koinViewModel


@Composable
fun StartScreen(
    authenticationNavigationListener: AuthenticationNavigationListener,
    client: SignInClient,
    viewModel: StartScreenViewModel = koinViewModel()
) {

    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        viewModel.authWithGoogleIntent(result, client, authenticationNavigationListener)
    }

    val authenticationListener = viewModel.getAuthenticationListener(authenticationNavigationListener)

    val scrollState = rememberScrollState()

    val isLoading by viewModel.isLoading.collectAsState()

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

            Image(
                painter = painterResource(id = R.drawable.main_image),
                contentDescription = stringResource(id = R.string.main_image),
                modifier = Modifier.size(size = 256.dp)
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            Text(
                text = stringResource(id = R.string.hello),
                style = TutorsScheduleTheme.typography.mainTitle,
                color = TutorsScheduleTheme.colors.detachedText
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            DefaultButton(text = stringResource(id = R.string.registration)) {
                viewModel.navigateToDataScreen(authenticationNavigationListener)
            }

            Spacer(modifier = Modifier.height(height = 16.dp))

            DefaultButton(text = stringResource(id = R.string.entrance)) {
                viewModel.navigateToLoginScreen(authenticationNavigationListener)
            }

            Spacer(modifier = Modifier.height(height = 16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val width = (LocalConfiguration.current.screenWidthDp.dp - (16.dp * 2) - 48.dp) / 2

                Divider(
                    modifier = Modifier.width(width = width),
                    thickness = 1.dp,
                    color = TutorsScheduleTheme.colors.hints
                )

                Text(
                    text = "или",
                    modifier = Modifier.width(width = 48.dp),
                    textAlign = TextAlign.Center,
                    color = TutorsScheduleTheme.colors.hints,
                    style = TutorsScheduleTheme.typography.hints
                )

                Divider(
                    modifier = Modifier.width(width = width),
                    thickness = 1.dp,
                    color = TutorsScheduleTheme.colors.hints
                )
            }


            Spacer(modifier = Modifier.height(height = 16.dp))

            AuthenticationGoogleButton {
                viewModel.authWithGoogle(client = client, launcher = googleLauncher)
            }

            Spacer(modifier = Modifier.height(height = 16.dp))

            YandexAuthenticationButton(authenticationListener = authenticationListener)

            Spacer(modifier = Modifier.height(height = 16.dp))

            VKAuthenticationButton(authenticationListener = authenticationListener)

            Spacer(modifier = Modifier.height(height = 32.dp))
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
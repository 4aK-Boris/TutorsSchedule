package dmitriy.losev.tutorsschedule.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.R
import dmitriy.losev.tutorsschedule.domain.usecases.FirebaseUseCases
import dmitriy.losev.tutorsschedule.presentation.viewmodels.EmptyViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun EmptyScreen(client: SignInClient, navController: NavController, viewModel: EmptyViewModel = koinViewModel()) {

    val firebaseUseCases = koinInject<FirebaseUseCases>()

    val userData by viewModel.userData.collectAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserData()
    }

    Column(
        modifier = Modifier.fillMaxSize(),//.background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(userData?.profilePictureUrl)
                .crossfade(500)
                .build(),
            placeholder = painterResource(id = R.drawable.avatar_placeholder),
            contentDescription = stringResource(id = R.string.avatar_content_description),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.High,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(size = 196.dp)
        )

        userData?.username?.let { userName ->
            Text(text = userName)
        }

        userData?.email?.let { email ->
            Text(text = "email: $email")
        }

        Text(text = "Почта подтверждена - ${userData?.isEmailVerified}")

        Button(onClick = {
            scope.launch {
                if (userData == null) {
                    viewModel.navigateToAuthenticationActivity(navController = navController)
                } else {
                    firebaseUseCases.signOut(client = client)
                    viewModel.clearData()
                }
                viewModel.getUserData()
            }
        }) {
            if (userData == null) {
                Text(text = "Войти")
            } else  {
                Text(text = "Выйти")
            }
        }
    }
}
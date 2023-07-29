package dmitriy.losev.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.auth.R
import dmitriy.losev.auth.presentation.viewmodels.PasswordScreenViewModel
import dmitriy.losev.firebase.domain.models.UserDescription
import org.koin.androidx.compose.koinViewModel

@Composable
fun PasswordScreen(
    navController: NavController,
    userDescription: UserDescription,
    viewModel: PasswordScreenViewModel = koinViewModel()
) {

    val password1 by viewModel.password1.collectAsState()
    val password2 by viewModel.password2.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val scrollState = rememberScrollState()

    println(userDescription.imageUri)

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(height = 64.dp))

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

        Spacer(modifier = Modifier.height(height = 64.dp))

        Text(text = userDescription.email)

        Spacer(modifier = Modifier.height(height = 24.dp))

        TextField(
            value = password1,
            onValueChange = viewModel::onPassword1Changed
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        TextField(
            value = password2,
            onValueChange = viewModel::onPassword2Changed
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        Button(onClick = {
            viewModel.registration(
                userDescription = userDescription,
                navController = navController
            )
        }) {
            Text(text = "Зарегистрироваться")
        }

        Spacer(modifier = Modifier.height(height = 24.dp))

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(size = 64.dp),
                strokeWidth = 3.dp,
                strokeCap = StrokeCap.Square,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(height = 32.dp))
    }
}
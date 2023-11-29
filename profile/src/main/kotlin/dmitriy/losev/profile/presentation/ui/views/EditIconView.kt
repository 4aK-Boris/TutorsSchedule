package dmitriy.losev.profile.presentation.ui.views

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dmitriy.losev.core.theme.TutorsScheduleTheme
import dmitriy.losev.profile.R
import dmitriy.losev.profile.presentation.viewmodels.EditProfileScreenViewModel

private val ICON_SIZE = 200.dp

@Composable
fun EditIconView(viewModel: EditProfileScreenViewModel) {

    val uri by viewModel.avatarUri.collectAsState()
    val isAvatarChanged by viewModel.isAvatarChanged.collectAsState(initial = false)

    val borderWidth by animateDpAsState(
        targetValue = if (isAvatarChanged) 0.dp else 10.dp,
        label = "borderWidth",
        animationSpec = spring()
    )

    val infiniteTransition = rememberInfiniteTransition(label = "gradient")

    val gradientPosition by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "gradient"
    )

    val pickImage =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = viewModel::onUriChanged
        )

    IconButton(
        onClick = {
            pickImage.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(size = ICON_SIZE)
            .border(
                width = borderWidth,
                brush = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.9f to TutorsScheduleTheme.colors.iconBorder,
                        gradientPosition to TutorsScheduleTheme.colors.transparent
                    )
                ),
                shape = CircleShape
            )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = uri).crossfade(enable = true).build(),
            error = painterResource(id = R.drawable.avatar_placeholder),
            contentDescription = stringResource(id = R.string.avatar_content_description),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.High,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(size = ICON_SIZE - borderWidth * 2)
        )
    }
}
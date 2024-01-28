package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cameraswitch
import androidx.compose.material.icons.rounded.FlashlightOff
import androidx.compose.material.icons.rounded.FlashlightOn
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dmitriy.losev.profile.core.CameraState
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors

@Composable
fun CameraButtons(
    cameraState: CameraState,
    torchState: Boolean,
    onCameraStateChanged: () -> Unit,
    onTorchStateChanged: () -> Unit,
    takePhoto: () -> Unit
) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundIconPrimary

    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .padding(vertical = 39.dp)
                .height(40.dp)
                .width(width = 128.dp)
                .background(color = backgroundColor, shape = CircleShape)
                .align(alignment = Alignment.BottomCenter)
        )

        ButtonCamera(
            modifier = Modifier.align(Alignment.BottomEnd),
            cameraState = cameraState,
            onCameraStateChanged = onCameraStateChanged
        )

        ButtonTorch(
            modifier = Modifier.align(Alignment.BottomStart),
            cameraState = cameraState,
            torchState = torchState,
            onTorchStateChanged = onTorchStateChanged
        )

        ButtonCenter(
            modifier = Modifier.align(Alignment.BottomCenter),
            takePhoto = takePhoto
        )
    }
}

@Composable
private fun ButtonCamera(
    modifier: Modifier = Modifier,
    cameraState: CameraState,
    onCameraStateChanged: () -> Unit
) {

    val cameraRotate by animateFloatAsState(
        targetValue = cameraState.rotate,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "cameraRotate"
    )

    FloatingButtonIcon(
        modifier = modifier
            .rotate(cameraRotate)
            .padding(vertical = 32.dp, horizontal = 16.dp),
        imageVector = Icons.Rounded.Cameraswitch,
        contentDescription = "Переключить камеру",
        onClick = onCameraStateChanged,
    )
}

@Composable
private fun ButtonTorch(
    modifier: Modifier = Modifier,
    torchState: Boolean,
    cameraState: CameraState,
    onTorchStateChanged: () -> Unit
) {
    Crossfade(
        modifier = modifier,
        targetState = if (torchState) Icons.Rounded.FlashlightOn else Icons.Rounded.FlashlightOff,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
        label = "torchButton"
    ) {
        FloatingButtonIcon(
            imageVector = it,
            enabled = cameraState.torch,
            contentDescription = "Включить/Выключить фонарик",
            onClick = onTorchStateChanged,
            modifier = modifier.padding(vertical = 32.dp, horizontal = 16.dp)
        )
    }
}

@Composable
private fun ButtonCenter(
    modifier: Modifier,
    takePhoto: () -> Unit
) {

    FloatingButtonIcon(
        modifier = modifier.padding(vertical = 23.dp),
        imageVector = Icons.Rounded.PhotoCamera,
        contentDescription = "screen.contentDescription",
        onClick = takePhoto,
        size = 72.dp
    )
}

@Composable
private fun FloatingButtonIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    size: Dp = 54.dp,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val colors = LocalTutorsScheduleColors.current

    val backgroundColor = colors.backgroundIconPrimary
    val iconColor = colors.iconPrimary

    IconButton(
        enabled = enabled,
        modifier = modifier
            .size(size = size)
            .background(backgroundColor, shape = CircleShape), onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(28.dp),
            tint = iconColor
        )
    }
}
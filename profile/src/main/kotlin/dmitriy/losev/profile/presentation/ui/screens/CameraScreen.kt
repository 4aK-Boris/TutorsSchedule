package dmitriy.losev.profile.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.presentation.ui.views.CameraButtons
import dmitriy.losev.profile.presentation.viewmodels.CameraScreenViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("ClickableViewAccessibility")
@Composable
fun CameraScreen(profileNavigationListener: ProfileNavigationListener, viewModel: CameraScreenViewModel = koinViewModel()) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val previewView = viewModel.getPreviewView(context)

    val cameraState by viewModel.cameraState.collectAsState()
    val torchState by viewModel.torchState.collectAsState()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { previewView }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.addListener(lifecycleOwner, context, previewView)
    }

    CameraButtons(
        cameraState = cameraState,
        torchState = torchState,
        onCameraStateChanged = viewModel::onCameraStateChanged,
        onTorchStateChanged = viewModel::onTorchStateChanged
    ) {
        viewModel.takePhoto(profileNavigationListener, context)
    }
}

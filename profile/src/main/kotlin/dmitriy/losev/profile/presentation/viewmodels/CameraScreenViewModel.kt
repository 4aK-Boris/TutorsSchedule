package dmitriy.losev.profile.presentation.viewmodels

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import dmitriy.losev.profile.core.CameraState
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.camera.CameraAddListenerUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetPreviewViewUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraPhotoUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class CameraScreenViewModel(
    private val cameraGetPreviewViewUseCase: CameraGetPreviewViewUseCase,
    private val cameraPhotoUseCase: CameraPhotoUseCase,
    private val cameraAddListenerUseCase: CameraAddListenerUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases
) : BaseViewModel() {

    private val _cameraState = MutableStateFlow(value = CameraState.FRONT_CAMERA)
    private val _torchState = MutableStateFlow(value = false)

    val cameraState = _cameraState.asStateFlow()
    val torchState = _torchState.asStateFlow()

    private val cameraSelector = _cameraState.map { cameraState -> cameraState.cameraSelector }

    fun onCameraStateChanged() {
        _cameraState.value = _cameraState.value.changeCamera()
    }

    fun onTorchStateChanged() {
        _torchState.value = _torchState.value.not()
    }

    fun getPreviewView(context: Context): PreviewView {
        return cameraGetPreviewViewUseCase.getPreviewView(context)
    }

    fun takePhoto(profileNavigationListener: ProfileNavigationListener, context: Context) = runOnMain {
        cameraPhotoUseCase.takePhoto(context) { uri ->
            println(uri)
            runOnMain { profileNavigationUseCases.navigateToEditProfileScreen(profileNavigationListener, uri) }
        }
    }

    fun addListener(
        lifecycleOwner: LifecycleOwner,
        context: Context,
        previewView: PreviewView
    ) {
        cameraAddListenerUseCase.addListener(lifecycleOwner, context, previewView, cameraSelector, torchState)
    }
}
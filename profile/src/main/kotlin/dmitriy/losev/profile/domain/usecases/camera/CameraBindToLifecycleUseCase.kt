package dmitriy.losev.profile.domain.usecases.camera

import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.LifecycleOwner
import dmitriy.losev.profile.core.ProfileBaseUseCase

class CameraBindToLifecycleUseCase(private val imageCapture: ImageCapture): ProfileBaseUseCase() {

    fun bindToLifecycle(lifecycleOwner: LifecycleOwner, cameraSelector: CameraSelector, preview: Preview, cameraProvider: ProcessCameraProvider): Camera {
        cameraProvider.unbindAll()
        return cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)
    }
}
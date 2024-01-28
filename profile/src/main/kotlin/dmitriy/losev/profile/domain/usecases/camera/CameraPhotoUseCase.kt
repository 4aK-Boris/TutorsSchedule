package dmitriy.losev.profile.domain.usecases.camera

import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import dmitriy.losev.profile.core.ProfileBaseUseCase
import java.util.concurrent.Executor

class CameraPhotoUseCase(
    private val imageCapture: ImageCapture,
    private val executor: Executor,
    private val cameraGetOutputOptionsUseCase: CameraGetOutputOptionsUseCase
) : ProfileBaseUseCase() {

    fun takePhoto(context: Context, callback: (Uri?) -> Unit) {
        val outputOptions = cameraGetOutputOptionsUseCase.getOutputOptions(context)
        imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {

            override fun onError(exc: ImageCaptureException) {
                return callback(null)
            }

            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                callback(output.savedUri)
            }
        }
        )
    }
}
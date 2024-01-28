package dmitriy.losev.profile.domain.usecases.camera

import android.content.Context
import android.view.ScaleGestureDetector
import androidx.camera.core.Camera

class CameraGetScaleGestureDetectorUseCase(private val cameraGetSimpleOnScaleGestureListenerUseCase: CameraGetSimpleOnScaleGestureListenerUseCase) {

    fun getScaleGestureDetector(context: Context, camera: Camera): ScaleGestureDetector {
        val listener = cameraGetSimpleOnScaleGestureListenerUseCase.getSimpleOnScaleGestureListener(camera)
        return ScaleGestureDetector(context, listener)
    }
}
package dmitriy.losev.profile.domain.usecases.camera

import android.view.ScaleGestureDetector
import androidx.camera.core.Camera

class CameraGetSimpleOnScaleGestureListenerUseCase {

    fun getSimpleOnScaleGestureListener(camera: Camera): ScaleGestureDetector.SimpleOnScaleGestureListener {
        return object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val zoom = camera.cameraInfo.zoomState.value?.zoomRatio ?: 1f
                val delta = detector.scaleFactor
                camera.cameraControl.setZoomRatio(zoom * delta)
                return true
            }
        }
    }
}
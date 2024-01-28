package dmitriy.losev.profile.core

import androidx.camera.core.CameraSelector

enum class CameraState(val cameraSelector: CameraSelector, val torch: Boolean, val rotate: Float) {

    BACK_CAMERA(cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA, torch = true, rotate = 360f),
    FRONT_CAMERA(cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA, torch = false, rotate = 0f);

    fun changeCamera(): CameraState {
        return if (this == BACK_CAMERA) {
            FRONT_CAMERA
        } else {
            BACK_CAMERA
        }
    }
}
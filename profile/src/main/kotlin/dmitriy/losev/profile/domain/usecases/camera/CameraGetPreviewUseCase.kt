package dmitriy.losev.profile.domain.usecases.camera

import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import dmitriy.losev.profile.core.ProfileBaseUseCase

class CameraGetPreviewUseCase: ProfileBaseUseCase() {

    fun getPreview(previewView: PreviewView): Preview {
        return Preview.Builder().build().also { preview ->
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
    }
}
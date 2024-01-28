package dmitriy.losev.profile.domain.usecases.camera

import android.content.Context
import android.view.ViewGroup
import androidx.camera.view.PreviewView
import dmitriy.losev.profile.core.ProfileBaseUseCase

class CameraGetPreviewViewUseCase: ProfileBaseUseCase() {

    fun getPreviewView(context: Context): PreviewView {
        return PreviewView(context).apply {
            this.scaleType = PreviewView.ScaleType.FILL_CENTER
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }
}
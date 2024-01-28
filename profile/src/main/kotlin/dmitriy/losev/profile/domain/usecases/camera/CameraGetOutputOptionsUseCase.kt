package dmitriy.losev.profile.domain.usecases.camera

import android.content.Context
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import dmitriy.losev.profile.core.ProfileBaseUseCase

class CameraGetOutputOptionsUseCase(private val cameraGetContentValuesUseCase: CameraGetContentValuesUseCase): ProfileBaseUseCase() {

    fun getOutputOptions(context: Context): ImageCapture.OutputFileOptions {
        val contentValues = cameraGetContentValuesUseCase.getContentValues()
        return ImageCapture.OutputFileOptions.Builder(context.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build()
    }
}
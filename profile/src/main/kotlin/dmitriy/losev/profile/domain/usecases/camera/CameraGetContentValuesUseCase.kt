package dmitriy.losev.profile.domain.usecases.camera

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import dmitriy.losev.profile.core.ProfileBaseUseCase

class CameraGetContentValuesUseCase(private val cameraGetFileNameUseCase: CameraGetFileNameUseCase) : ProfileBaseUseCase() {

    fun getContentValues(): ContentValues {
        val fileName = cameraGetFileNameUseCase.getFileName()
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, TYPE)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, PATH)
            }
        }
    }

    companion object {

        private const val TYPE = "image/jpeg"
        private const val PATH = "Pictures/CameraX-Image"
    }
}
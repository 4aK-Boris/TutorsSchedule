package dmitriy.losev.profile.domain.usecases.camera

import dmitriy.losev.profile.core.ProfileBaseUseCase
import java.text.SimpleDateFormat
import java.util.Locale

class CameraGetFileNameUseCase(private val cameraGetTimeUseCase: CameraGetTimeUseCase): ProfileBaseUseCase() {

    fun getFileName(): String {
        val time = cameraGetTimeUseCase.getTime()
        return SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(time)
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}
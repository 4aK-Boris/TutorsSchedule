package dmitriy.losev.profile.core

import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import org.koin.dsl.module

val cameraModule = module {

    single { ImageCapture.Builder().build() }

    single { ProcessCameraProvider.getInstance(get()) }

    single { ContextCompat.getMainExecutor(get()) }
}
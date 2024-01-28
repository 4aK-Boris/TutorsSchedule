package dmitriy.losev.profile.domain.usecases.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import dmitriy.losev.profile.core.ProfileBaseUseCase
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executor

class CameraAddListenerUseCase(
    private val executor: Executor,
    private val cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    private val cameraGetListenerUseCase: CameraGetListenerUseCase
) : ProfileBaseUseCase() {

    fun addListener(lifecycleOwner: LifecycleOwner, context: Context, previewView: PreviewView, cameraSelectorFlow: Flow<CameraSelector>, torchFlow: Flow<Boolean>,) {
        val listener = cameraGetListenerUseCase.getListener(lifecycleOwner, previewView, context, cameraSelectorFlow, torchFlow, cameraProviderFuture)
        cameraProviderFuture.addListener(listener, executor)
    }
}
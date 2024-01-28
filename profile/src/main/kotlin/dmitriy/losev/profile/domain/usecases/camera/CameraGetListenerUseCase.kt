package dmitriy.losev.profile.domain.usecases.camera

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.ui.core.afterMeasured
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CameraGetListenerUseCase(
    private val cameraBindToLifecycleUseCase: CameraBindToLifecycleUseCase,
    private val cameraGetTouchListenersUseCase: CameraGetTouchListenersUseCase,
    private val cameraGetPreviewUseCase: CameraGetPreviewUseCase
) : ProfileBaseUseCase() {

    private val scope = CoroutineScope(context = Dispatchers.Main)

    @SuppressLint("ClickableViewAccessibility")
    fun getListener(
        lifecycleOwner: LifecycleOwner,
        previewView: PreviewView,
        context: Context,
        cameraSelectorFlow: Flow<CameraSelector>,
        torchFlow: Flow<Boolean>,
        cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    ): Runnable {

        val preview = cameraGetPreviewUseCase.getPreview(previewView)

        return Runnable {
            try {

                val cameraProvider = cameraProviderFuture.get()

                var camera = cameraBindToLifecycleUseCase.bindToLifecycle(
                    lifecycleOwner = lifecycleOwner,
                    cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA,
                    cameraProvider = cameraProvider,
                    preview = preview,

                    )

                scope.launch {
                    cameraSelectorFlow.collectLatest { cameraSelector ->
                        camera = cameraBindToLifecycleUseCase.bindToLifecycle(
                            lifecycleOwner = lifecycleOwner,
                            cameraSelector = cameraSelector,
                            cameraProvider = cameraProvider,
                            preview = preview
                        )
                    }
                }

                val firstOnTouchListener = cameraGetTouchListenersUseCase.getFirstTouchListener(context, camera)
                val secondOnTouchListener = cameraGetTouchListenersUseCase.getSecondTouchListener(camera)

                previewView.setOnTouchListener(firstOnTouchListener)
                previewView.afterMeasured {
                    previewView.setOnTouchListener(secondOnTouchListener)
                }

                scope.launch {
                    torchFlow.collectLatest { torch ->
                        camera.cameraControl.enableTorch(torch)
                    }
                }


            } catch (exc: Exception) {
                Log.e(ContentValues.TAG, "Use case binding failed", exc)
            }
        }
    }
}
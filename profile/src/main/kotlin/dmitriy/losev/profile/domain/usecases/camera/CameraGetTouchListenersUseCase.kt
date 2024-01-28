package dmitriy.losev.profile.domain.usecases.camera

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.camera.core.Camera
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.MeteringPointFactory
import androidx.camera.core.SurfaceOrientedMeteringPointFactory
import dmitriy.losev.profile.core.ProfileBaseUseCase

class CameraGetTouchListenersUseCase(private val cameraGetScaleGestureDetectorUseCase: CameraGetScaleGestureDetectorUseCase) : ProfileBaseUseCase() {

    @SuppressLint("ClickableViewAccessibility")
    fun getFirstTouchListener(context: Context, camera: Camera): View.OnTouchListener {
        val scaleGestureDetector = cameraGetScaleGestureDetectorUseCase.getScaleGestureDetector(context, camera)
        return View.OnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            return@OnTouchListener true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun getSecondTouchListener(camera: Camera): View.OnTouchListener {
        return View.OnTouchListener { view, event ->
            return@OnTouchListener when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    val factory: MeteringPointFactory =
                        SurfaceOrientedMeteringPointFactory(view.width.toFloat(), view.height.toFloat())
                    val autoFocusPoint = factory.createPoint(event.x, event.y)
                    camera.cameraControl.startFocusAndMetering(
                        FocusMeteringAction.Builder(autoFocusPoint, FocusMeteringAction.FLAG_AF).apply { disableAutoCancel() }.build()
                    )
                    true
                }
                else -> false
            }
        }
    }
}
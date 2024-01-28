package dmitriy.losev.profile.domain.di

import dmitriy.losev.profile.domain.usecases.camera.CameraAddListenerUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraBindToLifecycleUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetContentValuesUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetFileNameUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetListenerUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetOutputOptionsUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetPreviewUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetPreviewViewUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetScaleGestureDetectorUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetSimpleOnScaleGestureListenerUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetTimeUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraGetTouchListenersUseCase
import dmitriy.losev.profile.domain.usecases.camera.CameraPhotoUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cameraUseCaseModule = module {

    factoryOf(::CameraGetPreviewViewUseCase)

    factoryOf(::CameraGetSimpleOnScaleGestureListenerUseCase)

    factoryOf(::CameraGetScaleGestureDetectorUseCase)

    factoryOf(::CameraGetTouchListenersUseCase)

    factoryOf(::CameraGetPreviewUseCase)

    factoryOf(::CameraBindToLifecycleUseCase)

    factoryOf(::CameraGetListenerUseCase)

    factoryOf(::CameraAddListenerUseCase)

    factoryOf(::CameraPhotoUseCase)

    factoryOf(::CameraGetFileNameUseCase)

    factoryOf(::CameraGetTimeUseCase)

    factoryOf(::CameraGetContentValuesUseCase)

    factoryOf(::CameraGetOutputOptionsUseCase)
}
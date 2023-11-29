package dmitriy.losev.profile.core

import com.google.firebase.auth.FirebaseAuthException
import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.profile.core.exception.FIREBASE_AUTH_EXCEPTION_CODE
import kotlin.reflect.KClass

abstract class ProfileBaseUseCase(errorHandler: ErrorHandler) :
    BaseUseCase(errorHandler = errorHandler) {

    override val exceptions: Map<KClass<out Throwable>, Int> = mapOf(
        FirebaseAuthException::class to FIREBASE_AUTH_EXCEPTION_CODE
    )
}
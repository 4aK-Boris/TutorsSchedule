package dmitriy.losev.firebase.core

import com.google.android.gms.common.api.UnsupportedApiCallException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_USER_EXCEPTION
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_TOO_MANY_REQUESTS_EXCEPTION
import dmitriy.losev.firebase.core.exception.UNSUPPORTED_API_CALL_EXCEPTION
import kotlin.reflect.KClass

abstract class FirebaseBaseUseCase(errorHandler: ErrorHandler) :
    BaseUseCase(errorHandler = errorHandler) {

    override val exceptions: Map<KClass<out Throwable>, Int> = mapOf(
        FirebaseAuthInvalidUserException::class to FIREBASE_AUTH_INVALID_USER_EXCEPTION,
        FirebaseAuthInvalidCredentialsException::class to FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION,
        FirebaseTooManyRequestsException::class to FIREBASE_TOO_MANY_REQUESTS_EXCEPTION,
        UnsupportedApiCallException::class to UNSUPPORTED_API_CALL_EXCEPTION,
        FirebaseAuthUserCollisionException::class to FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE
    )
}
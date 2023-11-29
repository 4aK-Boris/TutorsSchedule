package dmitriy.losev.firebase.core

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.UnsupportedApiCallException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.core.exception.API_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_RECENT_LOGIN_REQUIRED_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE
import dmitriy.losev.firebase.core.exception.UNSUPPORTED_API_CALL_EXCEPTION
import kotlin.reflect.KClass

abstract class FirebaseBaseUseCase(errorHandler: ErrorHandler) :
    BaseUseCase(errorHandler = errorHandler) {

    override val exceptions: Map<KClass<out Throwable>, Int> = mapOf(
        FirebaseAuthInvalidUserException::class to FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE,
        FirebaseAuthInvalidCredentialsException::class to FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE,
        FirebaseTooManyRequestsException::class to FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE,
        UnsupportedApiCallException::class to UNSUPPORTED_API_CALL_EXCEPTION,
        FirebaseAuthUserCollisionException::class to FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE,
        FirebaseAuthRecentLoginRequiredException::class to FIREBASE_AUTH_RECENT_LOGIN_REQUIRED_EXCEPTION_CODE,
        ApiException::class to API_EXCEPTION_CODE
    )
}
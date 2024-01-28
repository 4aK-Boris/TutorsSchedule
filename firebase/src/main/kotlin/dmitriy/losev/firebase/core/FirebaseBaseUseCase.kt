package dmitriy.losev.firebase.core

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.UnsupportedApiCallException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import dmitriy.losev.core.BaseUseCase
import dmitriy.losev.exception.BaseException
import kotlin.reflect.KClass

abstract class FirebaseBaseUseCase : BaseUseCase() {

    override val exceptions: Map<KClass<out Throwable>, BaseException> = mapOf(
        FirebaseAuthInvalidUserException::class to dmitriy.losev.firebase.core.exception.FirebaseAuthInvalidUserException(),
        FirebaseAuthInvalidCredentialsException::class to dmitriy.losev.firebase.core.exception.FirebaseAuthInvalidCredentialsException(),
        FirebaseTooManyRequestsException::class to dmitriy.losev.firebase.core.exception.FirebaseTooManyRequestsException(),
        UnsupportedApiCallException::class to dmitriy.losev.firebase.core.exception.UnsupportedApiCallException(),
        FirebaseAuthUserCollisionException::class to dmitriy.losev.firebase.core.exception.FirebaseAuthUserCollisionException(),
        FirebaseAuthRecentLoginRequiredException::class to dmitriy.losev.firebase.core.exception.FirebaseAuthRecentLoginRequiredException(),
        FirebaseAuthWeakPasswordException::class to dmitriy.losev.firebase.core.exception.FirebaseAuthWeakPasswordException(),
        ApiException::class to dmitriy.losev.firebase.core.exception.ApiException(),
        FirebaseNetworkException::class to dmitriy.losev.firebase.core.exception.FirebaseNetworkException()
    )
}
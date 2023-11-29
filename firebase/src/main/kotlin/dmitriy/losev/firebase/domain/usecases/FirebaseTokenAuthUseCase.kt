package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.TokenAuthenticationException
import dmitriy.losev.firebase.domain.models.FirebaseToken
import kotlinx.coroutines.tasks.await

class FirebaseTokenAuthUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
): FirebaseBaseUseCase(errorHandler) {

    suspend fun authWithToken(token: FirebaseToken): Result<FirebaseUser> = safeCall {
        val authResult = auth.signInWithCustomToken(token.token).await()
        authResult.user ?: throw TokenAuthenticationException()
    }
}
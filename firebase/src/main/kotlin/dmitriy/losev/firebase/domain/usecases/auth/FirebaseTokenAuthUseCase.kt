package dmitriy.losev.firebase.domain.usecases.auth

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.FirebaseToken
import kotlinx.coroutines.tasks.await

class FirebaseTokenAuthUseCase(private val auth: FirebaseAuth) : FirebaseBaseUseCase() {

    suspend fun authWithToken(token: FirebaseToken): Unit = convertException {
        auth.signInWithCustomToken(token.token).await()
    }
}
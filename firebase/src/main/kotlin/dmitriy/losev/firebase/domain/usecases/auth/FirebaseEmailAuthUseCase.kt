package dmitriy.losev.firebase.domain.usecases.auth

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseEmailAuthUseCase(private val auth: FirebaseAuth): FirebaseBaseUseCase() {

    suspend fun authWithEmail(email: String, password: String): Unit = convertException {
        auth.signInWithEmailAndPassword(email, password).await()
    }
}
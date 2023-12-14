package dmitriy.losev.firebase.domain.usecases.auth

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseEmailRegistrationUseCase(private val auth: FirebaseAuth): FirebaseBaseUseCase() {

    suspend fun registrationWithEmail(email: String, password: String): Unit = convertException {
        auth.createUserWithEmailAndPassword(email, password).await()
    }
}
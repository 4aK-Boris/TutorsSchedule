package dmitriy.losev.firebase.domain.usecases.auth

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseLogOutUseCase(private val auth: FirebaseAuth) : FirebaseBaseUseCase() {

    fun logOut() {
        auth.signOut()
    }
}
package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException

class FirebaseGetUserUseCase(private val auth: FirebaseAuth) : FirebaseBaseUseCase() {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun getUserWithException(): FirebaseUser = convertException {
        user ?: throw UserNotAuthorizationException()
    }

    suspend fun getUserWithoutException(): FirebaseUser? = convertExceptionWithNullable {
        user
    }
}
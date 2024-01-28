package dmitriy.losev.firebase.core.usecases

import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import kotlinx.coroutines.tasks.await

abstract class BaseAuthUseCaseTest: BaseUseCaseTest() {

    val user by lazy { auth.currentUser ?: throw UserNotAuthorizationException() }

    val isAuth: Boolean
        get() = auth.currentUser != null

    suspend fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun deleteAccount() {
        user.delete().await()
    }
}
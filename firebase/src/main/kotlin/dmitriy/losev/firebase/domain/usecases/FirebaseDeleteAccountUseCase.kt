package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.core.result.emptyResult
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseDeleteAccountUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth,
    private val firebaseLogOutUseCase: FirebaseLogOutUseCase
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun deleteAccount(): Result<Unit> = safeReturnCall {
        user?.let { user ->
            user.delete().await()
            firebaseLogOutUseCase.logOut()
        } ?: emptyResult
    }

    suspend fun deleteAccount(user: FirebaseUser): Result<Unit> = safeReturnCall {
        user.delete().await()
        firebaseLogOutUseCase.logOut()
    }
}
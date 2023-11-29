package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseLogOutUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
) : FirebaseBaseUseCase(errorHandler) {

    suspend fun logOut(): Result<Unit> = safeCall {
        auth.signOut()
    }
}
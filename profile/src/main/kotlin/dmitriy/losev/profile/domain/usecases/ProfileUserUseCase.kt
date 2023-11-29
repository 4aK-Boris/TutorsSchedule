package dmitriy.losev.profile.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseUserUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUserUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUserUseCase: FirebaseUserUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun getUserWithException(): Result<FirebaseUser> = safeReturnCall {
        firebaseUserUseCase.getUserWithException()
    }

    suspend fun getUserWithOutException(): Result<FirebaseUser> = safeReturnCall {
        firebaseUserUseCase.getUserWithoutException()
    }
}
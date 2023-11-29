package dmitriy.losev.profile.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseDeleteAccountUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileDeleteAccountUseCase(
    errorHandler: ErrorHandler,
    private val firebaseDeleteAccountUseCase: FirebaseDeleteAccountUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun deleteAccount(user: FirebaseUser): Result<Unit> = safeReturnCall {
        firebaseDeleteAccountUseCase.deleteAccount(user)
    }
}
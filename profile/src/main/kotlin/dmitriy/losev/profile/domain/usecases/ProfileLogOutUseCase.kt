package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseLogOutUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileLogOutUseCase(
    errorHandler: ErrorHandler,
    private val firebaseLogOutUseCase: FirebaseLogOutUseCase,
) : ProfileBaseUseCase(errorHandler) {

    suspend fun logOut(): Result<Unit> = safeReturnCall {
        firebaseLogOutUseCase.logOut()
    }
}
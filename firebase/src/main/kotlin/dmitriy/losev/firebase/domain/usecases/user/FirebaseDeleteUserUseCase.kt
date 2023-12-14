package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase

class FirebaseDeleteUserUseCase(
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase,
    private val firebaseLogOutUseCase: FirebaseLogOutUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteAccount() {
        deleteAccount(user = firebaseGetUserUseCase.getUserWithException())
    }

    suspend fun deleteAccount(user: FirebaseUser): Unit = convertException {
        user.delete()
        firebaseLogOutUseCase.logOut()
    }
}
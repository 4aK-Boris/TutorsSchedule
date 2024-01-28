package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseGetLastNameUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun getLastName(): String {
        return getLastName(user = firebaseGetUserUseCase.getUserWithException())
    }

    suspend fun getLastName(user: FirebaseUser): String {
        return firebaseUserDataRepository.getLastName(userId = user.uid)
    }
}
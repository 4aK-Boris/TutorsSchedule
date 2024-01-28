package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseGetFirstNameUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun getFirstName(): String  {
        return getFirstName(user = firebaseGetUserUseCase.getUserWithException())
    }

    suspend fun getFirstName(user: FirebaseUser): String {
        return firebaseUserDataRepository.getFirstName(userId = user.uid)
    }
}
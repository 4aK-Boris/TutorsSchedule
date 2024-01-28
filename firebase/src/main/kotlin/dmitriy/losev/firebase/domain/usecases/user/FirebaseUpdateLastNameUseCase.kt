package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseUpdateLastNameUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun updateLastName(lastName: String)  {
        return updateLastName(user = firebaseGetUserUseCase.getUserWithException(), lastName = lastName)
    }

    suspend fun updateLastName(user: FirebaseUser, lastName: String) {
        return firebaseUserDataRepository.updateLastName(userId = user.uid, lastName = lastName)
    }
}
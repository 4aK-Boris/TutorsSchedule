package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseUpdateFirstNameUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun updateFirstName(firstName: String)  {
        return updateFirstName(user = firebaseGetUserUseCase.getUserWithException(), firstName = firstName)
    }

    suspend fun updateFirstName(user: FirebaseUser, firstName: String) {
        return firebaseUserDataRepository.updateFirstName(userId = user.uid, firstName = firstName)
    }
}
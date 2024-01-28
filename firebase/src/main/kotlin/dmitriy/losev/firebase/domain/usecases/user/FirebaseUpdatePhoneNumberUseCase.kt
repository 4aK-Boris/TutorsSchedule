package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseUpdatePhoneNumberUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun updatePhoneNumber(phoneNumber: String)  {
        return updatePhoneNumber(user = firebaseGetUserUseCase.getUserWithException(), phoneNumber = phoneNumber)
    }

    suspend fun updatePhoneNumber(user: FirebaseUser, phoneNumber: String) {
        return firebaseUserDataRepository.updatePhoneNumber(userId = user.uid, phoneNumber = phoneNumber)
    }
}
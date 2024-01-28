package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseGetPhoneNumberUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun getPhoneNumber(): String  {
        return getPhoneNumber(user = firebaseGetUserUseCase.getUserWithException())
    }

    suspend fun getPhoneNumber(user: FirebaseUser): String {
        return firebaseUserDataRepository.getPhoneNumber(userId = user.uid)
    }
}
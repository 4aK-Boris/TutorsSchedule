package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseGetPatronymicUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun getPatronymic(): String  {
        return getPatronymic(user = firebaseGetUserUseCase.getUserWithException())
    }

    suspend fun getPatronymic(user: FirebaseUser): String {
        return firebaseUserDataRepository.getPatronymic(userId = user.uid)
    }
}
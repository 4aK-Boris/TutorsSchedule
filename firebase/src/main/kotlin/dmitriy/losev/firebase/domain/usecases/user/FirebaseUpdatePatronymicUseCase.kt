package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUserDataRepository

class FirebaseUpdatePatronymicUseCase(
    private val firebaseUserDataRepository: FirebaseUserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun updatePatronymic(patronymic: String)  {
        return updatePatronymic(user = firebaseGetUserUseCase.getUserWithException(), patronymic = patronymic)
    }

    suspend fun updatePatronymic(user: FirebaseUser, patronymic: String) {
        return firebaseUserDataRepository.updatePatronymic(userId = user.uid, patronymic = patronymic)
    }
}
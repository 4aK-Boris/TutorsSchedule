package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetPatronymicUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileGetNameUseCase(
    private val firebaseGetFirstNameUseCase: FirebaseGetFirstNameUseCase,
    private val firebaseGetLastNameUseCase: FirebaseGetLastNameUseCase,
    private val firebaseGetPatronymicUseCase: FirebaseGetPatronymicUseCase
): ProfileBaseUseCase() {

    suspend fun getDisplayName(): String? {
        val firstName = firebaseGetFirstNameUseCase.getFirstName()
        val lastName = firebaseGetLastNameUseCase.getLastName()
        val patronymic = firebaseGetPatronymicUseCase.getPatronymic()
        return "$lastName $firstName $patronymic".ifBlank { null }
    }
}
package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFormattedPhoneNumberUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetPhoneNumberUseCase

class ProfileGetPhoneNumberUseCase(
    private val firebaseGetPhoneNumberUseCase: FirebaseGetPhoneNumberUseCase,
    private val firebaseGetFormattedPhoneNumberUseCase: FirebaseGetFormattedPhoneNumberUseCase
) {

    suspend fun getPhoneNumber(): String? {
        return firebaseGetPhoneNumberUseCase.getPhoneNumber().ifBlank { null }
    }

    suspend fun getFormattedPhoneNumber(): String? {
        return firebaseGetFormattedPhoneNumberUseCase.getPhoneNumber().ifBlank { null }
    }
}
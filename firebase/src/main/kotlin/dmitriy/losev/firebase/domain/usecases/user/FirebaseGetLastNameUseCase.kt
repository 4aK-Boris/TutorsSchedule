package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseNameRepository

class FirebaseGetLastNameUseCase(
    private val firebaseNameRepository: FirebaseNameRepository,
    private val firebaseGetDisplayNameUseCase: FirebaseGetDisplayNameUseCase
) : FirebaseBaseUseCase() {

    suspend fun getLastName(): String? {
        val displayName = firebaseGetDisplayNameUseCase.getDisplayName()
        return firebaseNameRepository.getLastName(displayName)
    }

    suspend fun getLastName(user: FirebaseUser): String? {
        val displayName = firebaseGetDisplayNameUseCase.getDisplayName(user)
        return firebaseNameRepository.getLastName(displayName)
    }
}
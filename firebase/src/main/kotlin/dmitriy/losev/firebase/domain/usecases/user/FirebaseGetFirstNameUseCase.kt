package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseNameRepository

class FirebaseGetFirstNameUseCase(
    private val firebaseNameRepository: FirebaseNameRepository,
    private val firebaseGetDisplayNameUseCase: FirebaseGetDisplayNameUseCase
) : FirebaseBaseUseCase() {

    suspend fun getFirstName(): String?  {
        val displayName = firebaseGetDisplayNameUseCase.getDisplayName()
        return firebaseNameRepository.getFirstName(displayName)
    }

    suspend fun getFirstName(user: FirebaseUser): String? {
        val displayName = firebaseGetDisplayNameUseCase.getDisplayName(user)
        return firebaseNameRepository.getFirstName(displayName)
    }
}
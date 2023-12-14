package dmitriy.losev.firebase.domain.usecases.user

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseGetAvatarUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun getAvatarUri(): Uri?  {
        return firebaseGetUserUseCase.getUserWithException().photoUrl
    }

    fun getAvatarUri(user: FirebaseUser): Uri? {
        return user.photoUrl
    }
}
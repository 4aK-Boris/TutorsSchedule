package dmitriy.losev.firebase.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseAvatarUseCase(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth
) : FirebaseBaseUseCase(errorHandler) {

    private val user: FirebaseUser?
        get() = auth.currentUser

    suspend fun getAvatarUri(): Result<Uri> = safeCallNullable {
        user?.photoUrl
    }

    suspend fun getAvatarUri(user: FirebaseUser): Result<Uri> = safeCallNullable {
        user.photoUrl
    }
}
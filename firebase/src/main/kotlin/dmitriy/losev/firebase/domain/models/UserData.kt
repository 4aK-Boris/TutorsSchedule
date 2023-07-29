package dmitriy.losev.firebase.domain.models

import android.net.Uri
import java.io.Serializable

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: Uri?,
    val email: String?,
    val isEmailVerified: Boolean
): Serializable

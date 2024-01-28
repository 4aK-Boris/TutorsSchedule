package dmitriy.losev.profile.domain.models

import android.net.Uri

data class FullUserData(
    val avatarUri: Uri?,
    val displayName: String?,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val email: String?,
    val phoneNumber: String?,
    val hasEmailAndPasswordChanged: Boolean,
    val isEmailVerified: Boolean
)

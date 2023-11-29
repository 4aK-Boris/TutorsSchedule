package dmitriy.losev.profile.domain.models

import android.net.Uri

data class UserData(
    val avatarUri: Uri?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val provider: String?,
    val isEmailVerified: Boolean
)

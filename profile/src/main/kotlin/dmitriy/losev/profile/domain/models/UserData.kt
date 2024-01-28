package dmitriy.losev.profile.domain.models

import android.net.Uri

data class UserData(
    val avatarUri: Uri?,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?
)

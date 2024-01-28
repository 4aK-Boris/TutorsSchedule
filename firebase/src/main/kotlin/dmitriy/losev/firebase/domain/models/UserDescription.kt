package dmitriy.losev.firebase.domain.models

import android.net.Uri
import dmitriy.losev.core.serializers.UriSerializer
import kotlinx.serialization.Serializable

@Serializable
data class UserDescription(
    val firstName: String,
    val lastName: String,
    val email: String,
    @Serializable(with = UriSerializer::class) val imageUri: Uri?
)

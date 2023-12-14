package dmitriy.losev.firebase.domain.usecases.storage

import android.net.Uri
import com.google.firebase.storage.StorageReference
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseDeleteAvatarStorageUseCase(reference: StorageReference) : FirebaseBaseUseCase() {

    private val avatarReference by lazy { reference.child("avatars") }

    suspend fun deleteAvatar(imageUri: Uri?) {
        imageUri?.lastPathSegment?.let { lastPathSegment ->
            avatarReference.child(lastPathSegment).delete().await()
        }
    }
}
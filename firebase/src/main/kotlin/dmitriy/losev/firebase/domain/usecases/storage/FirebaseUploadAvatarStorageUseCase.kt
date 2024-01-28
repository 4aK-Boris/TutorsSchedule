package dmitriy.losev.firebase.domain.usecases.storage

import android.net.Uri
import com.google.firebase.storage.StorageReference
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUploadAvatarStorageUseCase(reference: StorageReference) : FirebaseBaseUseCase() {

    private val avatarsReference by lazy { reference.child("avatars") }

    suspend fun uploadAvatar(imageUri: Uri?): Uri? {
        return imageUri?.lastPathSegment?.let { lastPathSegment ->
            val riversRef = avatarsReference.child(lastPathSegment)
            riversRef.putFile(imageUri).await()
            riversRef.downloadUrl.await()
        }
    }
}
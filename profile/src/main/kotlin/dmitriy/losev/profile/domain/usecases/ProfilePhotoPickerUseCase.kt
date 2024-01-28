package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfilePhotoPickerUseCase: ProfileBaseUseCase() {

    fun launch(launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
        launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}
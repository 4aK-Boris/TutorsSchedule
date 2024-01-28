package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.repositories.UriRepository

class ProfileConvertUriUseCase(private val uriRepository: UriRepository): ProfileBaseUseCase() {

    suspend fun convertUriToString(uri: Uri?): String? {
        return uriRepository.convertUriToString(uri)
    }

    suspend fun convertStringToUri(uri: String?): Uri? {
        return uriRepository.convertStringToUri(uri)
    }
}
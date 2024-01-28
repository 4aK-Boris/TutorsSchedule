package dmitriy.losev.firebase.domain.usecases

import android.net.Uri
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.FirebaseUriRepository

class FirebaseUriUseCase(private val firebaseUriRepository: FirebaseUriRepository): FirebaseBaseUseCase() {

    fun convertUrlToUri(url: String): Uri {
        return firebaseUriRepository.convertUrlToUri(url)
    }
}
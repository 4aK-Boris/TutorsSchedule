package dmitriy.losev.firebase.domain.repositories

import android.net.Uri

interface FirebaseUriRepository {

    fun convertUrlToUri(url: String): Uri
}
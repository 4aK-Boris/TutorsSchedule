package dmitriy.losev.profile.domain.repositories

import android.net.Uri

interface UriRepository {

    suspend fun convertUriToString(uri: Uri?): String?

    suspend fun convertStringToUri(uri: String?): Uri?
}
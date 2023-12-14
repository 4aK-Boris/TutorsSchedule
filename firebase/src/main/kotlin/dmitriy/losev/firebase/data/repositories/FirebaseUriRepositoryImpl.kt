package dmitriy.losev.firebase.data.repositories

import android.net.Uri
import dmitriy.losev.firebase.data.mappers.UriMapper
import dmitriy.losev.firebase.domain.repositories.FirebaseUriRepository

class FirebaseUriRepositoryImpl(
    private val uriMapper: UriMapper
): FirebaseUriRepository {

    override fun convertUrlToUri(url: String): Uri {
        return uriMapper.map(url)
    }
}
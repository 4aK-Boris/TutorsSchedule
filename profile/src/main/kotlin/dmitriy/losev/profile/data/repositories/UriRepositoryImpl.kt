package dmitriy.losev.profile.data.repositories

import android.net.Uri
import dmitriy.losev.profile.data.mappers.UriMapper
import dmitriy.losev.profile.domain.repositories.UriRepository

class UriRepositoryImpl(private val uriMapper: UriMapper): UriRepository {

    override suspend fun convertStringToUri(uri: String?): Uri? {
        return uriMapper.map(value = uri)
    }

    override suspend fun convertUriToString(uri: Uri?): String? {
        return uriMapper.map(value = uri)
    }
}
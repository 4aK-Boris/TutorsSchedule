package dmitriy.losev.firebase.data.mappers

import android.net.Uri

class UriMapper {

    fun map(url: String?): Uri {
        return Uri.parse(url)
    }
}
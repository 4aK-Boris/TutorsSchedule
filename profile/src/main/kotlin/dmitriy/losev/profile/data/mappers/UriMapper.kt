package dmitriy.losev.profile.data.mappers

import android.net.Uri

class UriMapper {

    fun map(value: Uri?): String? {
        return value?.toString()?.replace(oldChar = '/', newChar = '*')
    }

    fun map(value: String?): Uri? {
        return value?.let { Uri.parse(value.replace(oldChar = '*', newChar = '/')) }
    }
}
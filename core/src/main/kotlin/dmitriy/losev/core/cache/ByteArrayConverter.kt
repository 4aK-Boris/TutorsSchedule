package dmitriy.losev.core.cache

class ByteArrayConverter {

    fun convertByteArrayToString(array: ByteArray): String {
        return array.toString(charset = Charsets.UTF_8)
    }

    fun convertStringToByteArray(str: String): ByteArray {
        return str.toByteArray(charset = Charsets.UTF_8)
    }
}
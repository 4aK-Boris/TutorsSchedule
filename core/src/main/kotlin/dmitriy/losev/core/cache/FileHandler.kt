package dmitriy.losev.core.cache

import android.net.Uri
import java.io.File

class FileHandler {

    fun readJsonFile(fileName: String, filePath: String): ByteArray? {
        val file = File("$filePath$fileName$FILE_EXTENSION")
        return if (file.exists()) {
            file.readBytes()
        } else {
            null
        }
    }

    fun readFile(fileName: String, filePath: String): ByteArray? {
        val file = File("$filePath$fileName")
        return if (file.exists()) {
            file.readBytes()
        } else {
            null
        }
    }

    fun readFile(uri: Uri?): ByteArray? {
        val file = File(uri.toString())
        return if (file.exists()) {
            file.readBytes()
        } else {
            null
        }
    }

    fun writeJsonFile(fileName: String, filePath: String, data: ByteArray) {
        val file = File("$filePath$fileName$FILE_EXTENSION")
        file.writeBytes(data)
    }

    fun writeFile(fileName: String, filePath: String, data: ByteArray) {
        val file = File("$filePath$fileName")
        file.writeBytes(data)
    }

    fun deleteFile(fileName: String, filePath: String) {
        val file = File("$filePath$fileName")
        if (file.exists()) {
            file.delete()
        }
    }

    companion object {
        private const val FILE_EXTENSION = ".json"
    }
}
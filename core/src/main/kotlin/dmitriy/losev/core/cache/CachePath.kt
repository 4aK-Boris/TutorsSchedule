package dmitriy.losev.core.cache

import android.content.Context
import dmitriy.losev.core.DATA_DIR
import dmitriy.losev.core.FILE_DIR

class CachePath(private val context: Context) {

    fun getFilePath(): String {
        return context.cacheDir.absolutePath + FILE_DIR
    }

    fun getDataPath(): String {
        return context.cacheDir.absolutePath + DATA_DIR
    }
}
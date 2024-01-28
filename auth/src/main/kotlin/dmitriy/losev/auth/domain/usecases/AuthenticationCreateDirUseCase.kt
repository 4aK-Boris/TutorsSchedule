package dmitriy.losev.auth.domain.usecases

import android.content.Context
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.core.DATA_DIR
import dmitriy.losev.core.FILE_DIR
import java.io.File

class AuthenticationCreateDirUseCase(private val context: Context): AuthenticationBaseUseCase() {

    fun createFirs() {
        val cacheDir = context.cacheDir.absolutePath
        val fileFile = File("$cacheDir$FILE_DIR")
        if (!fileFile.exists()) {
            fileFile.mkdir()
        }
        val dataFile = File("$cacheDir$DATA_DIR")
        if (!dataFile.exists()) {
            dataFile.mkdir()
        }
    }
}
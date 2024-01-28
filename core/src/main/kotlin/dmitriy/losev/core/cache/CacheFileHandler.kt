package dmitriy.losev.core.cache


class CacheFileHandler(private val cachePath: CachePath, private val fileHandler: FileHandler) {

    fun getFileData(name: String): ByteArray? {
        val dataPath = this.cachePath.getFilePath()
        return fileHandler.readFile(fileName = name, filePath = dataPath)
    }

    fun saveFile(name: String, data: ByteArray?) {
        val dataPath = this.cachePath.getFilePath()
        if (data != null) {
            fileHandler.writeFile(fileName = name, filePath = dataPath, data = data)
        } else {
            fileHandler.deleteFile(fileName = name, filePath = dataPath)
        }
    }
}
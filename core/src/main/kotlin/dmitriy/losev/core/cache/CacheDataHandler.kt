package dmitriy.losev.core.cache

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

class CacheDataHandler(private val cachePath: CachePath, private val byteArrayConverter: ByteArrayConverter, private val fileHandler: FileHandler) {

    fun <T : Any> loadData(name: String, deserializer: DeserializationStrategy<T>): T? {
        val dataPath = this.cachePath.getDataPath()
        return fileHandler.readJsonFile(fileName = name, filePath = dataPath)?.let { byteData ->
            val str = byteArrayConverter.convertByteArrayToString(array = byteData)
            return Json.decodeFromString(string = str, deserializer = deserializer)
        }
    }

    fun <T : Any> saveData(name: String, data: T, serializer: SerializationStrategy<T>) {
        val dataPath = this.cachePath.getDataPath()
        val str = Json.encodeToString(value = data, serializer = serializer)
        val byteData = byteArrayConverter.convertStringToByteArray(str)
        fileHandler.writeJsonFile(fileName = name, filePath = dataPath, data = byteData)
    }
}
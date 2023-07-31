package dmitriy.losev.yandex.data.repositories

import dmitriy.losev.yandex.data.dto.YandexUserDataDTO
import dmitriy.losev.yandex.data.mappers.YandexUserDataMapper
import dmitriy.losev.yandex.domain.models.YandexUserData
import dmitriy.losev.yandex.domain.repositories.YandexDataRepository
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class YandexDataRepositoryImpl(
    private val yandexUserDataMapper: YandexUserDataMapper
) : YandexDataRepository {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun getUserData(token: String): YandexUserData {
        val data = Base64.decode(token.split('.')[1]).decodeToString()
        println(data)
        val yandexUserDataDTO = Json.decodeFromString<YandexUserDataDTO>(string = data)
        return yandexUserDataMapper.map(value = yandexUserDataDTO)
    }

    override suspend fun verifyToken(token: String): Boolean {
//        val algorithm = HSAlgorithm.HS256(CLIENT_SECRET)
//        return KtJwtVerifier(algorithm = algorithm).verify(jwt = token)
        return true
    }

    companion object {
        private const val CLIENT_SECRET = "cfda7f7a1023416e946f554d1912e229"
    }
}
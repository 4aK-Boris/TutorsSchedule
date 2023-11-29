package dmitriy.losev.yandex.data.repositories

import dmitriy.losev.yandex.data.dto.YandexUserDataDTO
import dmitriy.losev.yandex.data.mappers.YandexUserDataMapper
import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.models.YandexUserData
import dmitriy.losev.yandex.domain.repositories.YandexDataRepository
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class YandexDataRepositoryImpl(private val yandexUserDataMapper: YandexUserDataMapper) : YandexDataRepository {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun getYandexUserData(yandexToken: YandexToken): YandexUserData {
        val data = Base64.decode(yandexToken.token.split('.')[1]).decodeToString()
        val yandexUserDataDTO = Json.decodeFromString<YandexUserDataDTO>(string = data)
        return yandexUserDataMapper.map(value = yandexUserDataDTO)
    }
}